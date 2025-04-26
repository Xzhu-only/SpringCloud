package com.example.student.affairs.dormitory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import com.example.student.affairs.dormitory.service.DormitoryRepairRequestService;
import com.example.student.affairs.dormitory.mapper.DormitoryRepairRequestMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【dormitory_repair_request(宿舍保修申请表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:07
*/
@Service
@RequiredArgsConstructor
public class DormitoryRepairRequestServiceImpl extends ServiceImpl<DormitoryRepairRequestMapper, DormitoryRepairRequest>
    implements DormitoryRepairRequestService{
    private final DormitoryRepairRequestMapper dormitoryRepairMapper;
    private final DormitoryMapper dormitoryMapper;
    private final UserClient userClient;

    @Override
    public Page<DormitoryRepairRequest> getPageByCondition(Page<DormitoryRepairRequest> dormitoryRepairRequestPage, DormitoryRepairRequest condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<DormitoryRepairRequest> queryWrapper = new QueryWrapper<>();
        if (condition.getDormitoryId() != null) {
            queryWrapper.lambda().eq(DormitoryRepairRequest::getDormitoryId, condition.getDormitoryId());
        } else if(user.getRole() == 2) {
            //如果登录用户是教师角色，则只查询自己负责的宿舍相关记录
            List<Integer> dormitoryIds = dormitoryMapper.selectListByTeacherId(userId).stream()
                    .map(Dormitory::getId).distinct().collect(Collectors.toList());
            if (!dormitoryIds.isEmpty()) {
                queryWrapper.lambda().in(DormitoryRepairRequest::getDormitoryId, dormitoryIds);
            } else {
                return dormitoryRepairRequestPage;
            }
        }
        if (StrUtil.isNotBlank(condition.getIssueType())) {
            queryWrapper.lambda().eq(DormitoryRepairRequest::getIssueType, condition.getIssueType());
        }
        queryWrapper.lambda().orderByDesc(DormitoryRepairRequest::getId);
        Page<DormitoryRepairRequest> page = dormitoryRepairMapper.selectPage(dormitoryRepairRequestPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addDormitoryRepairRequest(DormitoryRepairRequest dormitoryRepair) {
        dormitoryRepair.setRequestDate(new Date());
        return dormitoryRepairMapper.insert(dormitoryRepair) > 0;
    }

    @Override
    public boolean deleteDormitoryRepairRequestById(Integer id) {
        return dormitoryRepairMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editDormitoryRepairRequestById(DormitoryRepairRequest dormitoryRepair) {
        return dormitoryRepairMapper.updateById(dormitoryRepair) > 0;
    }

    private void fillDetail(List<DormitoryRepairRequest> list) {
        for (DormitoryRepairRequest repair : list) {
            Dormitory dormitory = dormitoryMapper.selectDormitoryById(repair.getDormitoryId());
            repair.setDormitoryName(dormitory.getDormitoryName());
        }
    }
}




