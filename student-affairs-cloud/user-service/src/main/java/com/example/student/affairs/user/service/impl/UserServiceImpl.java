package com.example.student.affairs.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.JwtUtil;
import com.example.student.affairs.common.utils.Md5Util;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.user.constant.RoleConstant;
import com.example.student.affairs.user.constant.StateConstant;
import com.example.student.affairs.user.mapper.CampusCardMapper;
import com.example.student.affairs.user.mapper.StudentCardMapper;
import com.example.student.affairs.user.mapper.StudentDormitoryMapper;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.CampusCard;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.StudentDormitory;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.model.dto.UserLoginDTO;
import com.example.student.affairs.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-15 16:22:34
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    private final UserMapper userMapper;
    private final CampusCardMapper campusCardMapper;
    private final StudentCardMapper studentCardMapper;
    private final StudentDormitoryMapper studentDormitoryMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userRegister(User user) {
        return this.addUser(user);
    }

    @Override
    public String userLogin(UserLoginDTO dto) {
        User user = userMapper.selectByUsername(dto.getUsername());
        //判断用户是否存在
        if (user == null){
            throw new BusinessException("用户名不存在！");
        }
        //判断是否是封禁用户
        if (user.getState().equals(StateConstant.UsersState.USERS_NOT_NORMAL)) {
            throw new BusinessException("您已被封禁！");
        }
        //判断密码是否正确
        if (!Md5Util.checkPassword(dto.getPassword(),user.getPassword())){
            throw new BusinessException("登录失败，密码错误！");
        }
        //生成token返回
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId",user.getId());
        return JwtUtil.genToken(userMap);
    }

    @Override
    public User getLoginInfo() {
        Integer userId = ThreadLocalUtil.get();
        return userMapper.selectById(userId);
    }

    @Override
    public Page<User> getPageByCondition(Page<User> page, User condition) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(condition.getUsername())) {
            queryWrapper.lambda().like(User::getUsername, condition.getUsername());
        }
        if (condition.getRole() != null) {
            queryWrapper.lambda().eq(User::getRole, condition.getRole());
        }
        if (condition.getState() != null) {
            queryWrapper.lambda().eq(User::getState, condition.getState());
        }
        queryWrapper.lambda().orderByDesc(User::getId);
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<User> getListByRole(Integer role) {
        User loginInfo = this.getLoginInfo();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //如果是教师用户且查询的是学生数据，则只查询自己负责的课程选课记录的学生用户数据
        if (loginInfo.getRole() == 2 && role == 1) {
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(User::getId, studentIds);
            }
        }
        queryWrapper.lambda().eq(User::getRole, role);
        queryWrapper.lambda().eq(User::getState, StateConstant.UsersState.USERS_NORMAL);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(User user) {
        //判断是否存在同名用户
        User tempUser = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(tempUser)) {
            throw new BusinessException("存在同名用户！");
        }
        user.setState(StateConstant.UsersState.USERS_NORMAL);
        //对密码进行加密
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        user.setCreateTime(new Date());
        return userMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserById(Integer id) {
        // 查询用户信息
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("删除失败，用户信息不存在！");
        }
        Integer role = user.getRole();
        //超级管理员不可删除
        if (role.equals(RoleConstant.ADMIN_ROLE)) {
            throw new BusinessException("删除失败，超级管理员用户无法删除！");
        }
        return userMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editUserById(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public Map<String, Integer> getActive() {
        HashMap<String, Integer> map = new HashMap<>();
        Integer userId = ThreadLocalUtil.get();
        //查询校园卡，判断是否注销
        CampusCard campusCard = campusCardMapper.selectByStudentId(userId);
        if (campusCard != null) {
            map.put("active", 0);
            map.put("id", campusCard.getId());
            return map;
        }
        //查询学生证，判断是否主席
        StudentCard studentCard = studentCardMapper.selectByStudentId(userId);
        if (studentCard != null) {
            map.put("active", 1);
            map.put("id", studentCard.getId());
            return map;
        }
        //查询住宿信息，判断是否退宿
        StudentDormitory studentDormitory = studentDormitoryMapper.selectByStudentId(userId);
        if (studentDormitory != null) {
            map.put("active", 2);
            map.put("id", studentDormitory.getId());
            return map;
        }
        map.put("active", 3);
        return map;
    }
}




