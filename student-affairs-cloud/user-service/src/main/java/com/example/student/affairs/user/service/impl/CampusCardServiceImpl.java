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
import com.example.student.affairs.user.model.StudentAwardPunishment;
import com.example.student.affairs.user.model.StudentCard;
import com.example.student.affairs.user.model.User;
import com.example.student.affairs.user.service.CampusCardService;
import com.example.student.affairs.user.mapper.CampusCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【campus_card(校园卡表)】的数据库操作Service实现
* @createDate 2024-12-19 19:00:58
*/
@Service
@RequiredArgsConstructor
public class CampusCardServiceImpl extends ServiceImpl<CampusCardMapper, CampusCard>
    implements CampusCardService{
    private final CampusCardMapper campusCardMapper;
    private final UserMapper userMapper;
    private final CourseSelectionClient courseSelectionClient;

    @Override
    public Page<CampusCard> getPageByCondition(Page<CampusCard> campusCardPage, CampusCard condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userMapper.selectUserById(userId);
        QueryWrapper<CampusCard> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(condition.getCardNumber())) {
            queryWrapper.lambda().like(CampusCard::getCardNumber, condition.getCardNumber());
        }
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(CampusCard::getStudentId, condition.getStudentId());
        } else if (user.getRole() == 2) {
            //如果是教师角色用户，则只查询自己负责的课程选课记录里的学生校园卡信息
            List<Integer> studentIds = courseSelectionClient.getList()
                    .stream().map(CourseSelection::getStudentId).distinct().collect(Collectors.toList());
            if (!studentIds.isEmpty()) {
                queryWrapper.lambda().in(CampusCard::getStudentId, studentIds);
            }
        }
        queryWrapper.lambda().orderByDesc(CampusCard::getId);
        Page<CampusCard> page = campusCardMapper.selectPage(campusCardPage, queryWrapper);
        this.fillCardDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addCampusCard(CampusCard campusCard) {
        //判断是否已经添加过校园卡
        Integer userId = ThreadLocalUtil.get();
        QueryWrapper<CampusCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CampusCard::getStudentId, userId);
        List<CampusCard> campusCardList = campusCardMapper.selectList(queryWrapper);
        if (!campusCardList.isEmpty()) {
            throw new BusinessException("添加校园卡失败，您已经添加过了！");
        }
        //生成10位不重复编号
        String cardNumber = generateCardNumber();
        campusCard.setCardNumber(cardNumber);
        campusCard.setIssuedDate(new Date());
        campusCard.setLastRechargeDate(new Date());
        return campusCardMapper.insert(campusCard) > 0;
    }

    @Override
    public boolean deleteCampusCardById(Integer id) {
        return campusCardMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editCampusCardById(CampusCard campusCard) {
        //判断余额是否有变化
        CampusCard temp = campusCardMapper.selectById(campusCard.getId());
        if (temp == null) {
            throw new BusinessException("修改失败，修改校园卡数据不存在！");
        }
        if (!campusCard.getBalance().equals(temp.getBalance())) {
            campusCard.setLastRechargeDate(new Date());
        }
        return campusCardMapper.updateById(campusCard) > 0;
    }

    private void fillCardDetail(List<CampusCard> list) {
        for (CampusCard card : list) {
            User user = userMapper.selectUserById(card.getStudentId());
            card.setStudentName(user.getUsername());
        }
    }

    // 生成10位不重复编号的方法
    private String generateCardNumber() {
        // 例如，生成当前时间戳加上一个随机数作为学生证编号
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 1000);  // 生成一个三位数的随机数
        return String.format("%010d", timestamp % 1000000000 + random);  // 保证是10位
    }
}




