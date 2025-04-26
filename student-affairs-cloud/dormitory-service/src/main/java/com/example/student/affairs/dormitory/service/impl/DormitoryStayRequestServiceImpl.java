package com.example.student.affairs.dormitory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryRepairRequest;
import com.example.student.affairs.dormitory.model.DormitoryStayRequest;
import com.example.student.affairs.dormitory.service.DormitoryStayRequestService;
import com.example.student.affairs.dormitory.mapper.DormitoryStayRequestMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【dormitory_stay_request(宿舍留校申请表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:07
*/
@Service
@RequiredArgsConstructor
public class DormitoryStayRequestServiceImpl extends ServiceImpl<DormitoryStayRequestMapper, DormitoryStayRequest>
    implements DormitoryStayRequestService{
    private final DormitoryStayRequestMapper dormitoryStayMapper;
    private final DormitoryMapper dormitoryMapper;
    private final UserClient userClient;

    @Override
    public Page<DormitoryStayRequest> getPageByCondition(Page<DormitoryStayRequest> dormitoryStayRequestPage, DormitoryStayRequest condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<DormitoryStayRequest> queryWrapper = new QueryWrapper<>();
        if (condition.getStudentId() != null) {
            queryWrapper.lambda().eq(DormitoryStayRequest::getStudentId, condition.getStudentId());
        }
        if (condition.getDormitoryId() != null) {
            queryWrapper.lambda().eq(DormitoryStayRequest::getDormitoryId, condition.getDormitoryId());
        } else if(user.getRole() == 2) {
            //如果登录用户是教师角色，则只查询自己负责的宿舍相关记录
            List<Integer> dormitoryIds = dormitoryMapper.selectListByTeacherId(userId).stream()
                    .map(Dormitory::getId).distinct().collect(Collectors.toList());
            if (!dormitoryIds.isEmpty()) {
                queryWrapper.lambda().in(DormitoryStayRequest::getDormitoryId, dormitoryIds);
            } else {
                return dormitoryStayRequestPage;
            }
        }
        if (StrUtil.isNotBlank(condition.getApprovalStatus())) {
            queryWrapper.lambda().eq(DormitoryStayRequest::getApprovalStatus, condition.getApprovalStatus());
        }
        queryWrapper.lambda().orderByDesc(DormitoryStayRequest::getId);
        Page<DormitoryStayRequest> page = dormitoryStayMapper.selectPage(dormitoryStayRequestPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addDormitoryStayRequest(DormitoryStayRequest dormitoryStay) {
       return dormitoryStayMapper.insert(dormitoryStay) > 0;
    }

    @Override
    public boolean deleteDormitoryStayRequestById(Integer id) {
        return dormitoryStayMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editDormitoryStayRequestById(DormitoryStayRequest dormitoryStay) {
        return dormitoryStayMapper.updateById(dormitoryStay) > 0;
    }

    private void fillDetail(List<DormitoryStayRequest> list) {
        for (DormitoryStayRequest stay : list) {
            Dormitory dormitory = dormitoryMapper.selectDormitoryById(stay.getDormitoryId());
            User student = userClient.getUserById(stay.getStudentId());
            stay.setDormitoryName(dormitory.getDormitoryName());
            stay.setStudentName(student.getUsername());
        }
    }
}




