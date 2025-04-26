package com.example.student.affairs.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.model.CourseSelection;
import com.example.student.affairs.user.mapper.UserMapper;
import com.example.student.affairs.user.model.CampusCard;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.service.StudentCardService;
import com.example.student.affairs.user.mapper.StudentCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【student_card(学生证表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:46
*/
@Service
@RequiredArgsConstructor
public class StudentCardServiceImpl extends ServiceImpl<StudentCardMapper, StudentCard>
    implements StudentCardService{
    private final StudentCardMapper studentCardMapper;
    private final UserMapper userMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<StudentCard> getPageByCondition(Page<StudentCard> studentCardPage, StudentCard condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<StudentCard> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(condition.getState())) {
            queryWrapper.lambda().eq(StudentCard::getState, condition.getState());
        }
        if (StrUtil.isNotBlank(condition.getCardNumber())) {
            queryWrapper.lambda().like(StudentCard::getCardNumber, condition.getCardNumber());
        }
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(StudentCard::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生学生证信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(StudentCard::getStudentId, studentIds);
            }
        }
        queryWrapper.lambda().orderByDesc(StudentCard::getId);
        Page<StudentCard> page = studentCardMapper.selectPage(studentCardPage, queryWrapper);
        this.fillCardDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addStudentCard(StudentCard studentCard) {
        //判断是否已经添加过学生证
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<StudentCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentCard::getStudentId, userId);
        List<StudentCard> studentCardList = studentCardMapper.selectList(queryWrapper);
        if (!studentCardList.isEmpty()) {
            throw new BusinessException("添加学生证失败，您已经添加过了！");
        }
        //生成学生证10位不重复编号
        String cardNumber = generateCardNumber();
        studentCard.setCardNumber(cardNumber);
        // 设置默认状态为“申请中”
        studentCard.setState("申请中");
        studentCard.setApplicationDate(new Date());
        return studentCardMapper.insert(studentCard) > 0;
    }

    @Override
    public boolean deleteStudentCardById(Integer id) {
        return studentCardMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editStudentCardById(StudentCard studentCard) {
        if (studentCard.getState().equals("已发放")) {
            studentCard.setIssueDate(new Date());
            // 设置过期日期为当前时间加四年
            Date expirationDate = addYearsToDate(new Date(), 4);
            studentCard.setExpirationDate(expirationDate);
        }
        return studentCardMapper.updateById(studentCard) > 0;
    }

    private void fillCardDetail(List<StudentCard> list) {
        for (StudentCard card : list) {
            User user = userMapper.selectUserById(card.getStudentId());
            card.setStudentName(user.getUsername());
        }
    }

    // 生成10位不重复学生证编号的方法
    private String generateCardNumber() {
        // 例如，生成当前时间戳加上一个随机数作为学生证编号
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 1000);  // 生成一个三位数的随机数
        return String.format("%010d", timestamp % 1000000000 + random);  // 保证是10位
    }

    // 给定日期增加指定年数的工具方法
    private Date addYearsToDate(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }
}




