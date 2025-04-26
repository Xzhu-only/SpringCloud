package com.example.student.affairs.dormitory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryStayRequest;
import com.example.student.affairs.dormitory.service.DormitoryService;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author zhanh
* @description 针对表【dormitory(宿舍表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:07
*/
@Service
@RequiredArgsConstructor
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory>
    implements DormitoryService{
    private final DormitoryMapper dormitoryMapper;
    private final UserClient userClient;

    @Override
    public Page<Dormitory> getPageByCondition(Page<Dormitory> dormitoryPage, Dormitory condition) {
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(condition.getDormitoryName())) {
            queryWrapper.lambda().like(Dormitory::getDormitoryName, condition.getDormitoryName());
        }
        if (condition.getTeacherId() != null) {
            queryWrapper.lambda().eq(Dormitory::getTeacherId, condition.getTeacherId());
        }
        queryWrapper.lambda().orderByDesc(Dormitory::getId);
        Page<Dormitory> page = dormitoryMapper.selectPage(dormitoryPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public List<Dormitory> getList() {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        //如果是教师角色，则只查询自己负责的宿舍
        if (user.getRole() == 2) {
            queryWrapper.lambda().eq(Dormitory::getTeacherId, userId);
        }
        return dormitoryMapper.selectList(queryWrapper);
    }

    @Override
    public boolean addDormitory(Dormitory dormitory) {
        //判断是否有同名宿舍
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dormitory::getDormitoryName, dormitory.getDormitoryName());
        List<Dormitory> dormitoryList = dormitoryMapper.selectList(queryWrapper);
        if (!dormitoryList.isEmpty()) {
            throw new BusinessException("添加失败，存在同名宿舍！");
        }
        dormitory.setCurrentOccupancy(0);
        dormitory.setCreateTime(new Date());
        return dormitoryMapper.insert(dormitory) > 0;
    }

    @Override
    public boolean deleteDormitoryById(Integer id) {
        return dormitoryMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editDormitoryById(Dormitory dormitory) {
        //查询原宿舍信息
        Dormitory temp = dormitoryMapper.selectById(dormitory.getId());
        if (temp == null) {
            throw new BusinessException("修改失败，宿舍信息不存在！");
        }
        //判断是否超过最大人数限制
        if (dormitory.getCurrentOccupancy() > temp.getCapacity()) {
            throw new BusinessException("修改失败，超过最大人数限制！");
        }
        return dormitoryMapper.updateById(dormitory) > 0;
    }

    @Override
    public Dormitory getDormitoryById(Integer id) {
        return dormitoryMapper.selectDormitoryById(id);
    }

    private void fillDetail(List<Dormitory> list) {
        for (Dormitory dormitory : list) {
            User teacher = userClient.getUserById(dormitory.getTeacherId());
            dormitory.setTeacherName(teacher.getUsername());
        }
    }
}




