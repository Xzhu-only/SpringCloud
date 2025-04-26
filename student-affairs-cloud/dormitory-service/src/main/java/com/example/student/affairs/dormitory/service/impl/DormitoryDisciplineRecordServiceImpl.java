package com.example.student.affairs.dormitory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryCleanlinessCheck;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import com.example.student.affairs.dormitory.service.DormitoryDisciplineRecordService;
import com.example.student.affairs.dormitory.mapper.DormitoryDisciplineRecordMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【dormitory_discipline_record(宿舍违纪记录表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:07
*/
@Service
@RequiredArgsConstructor
public class DormitoryDisciplineRecordServiceImpl extends ServiceImpl<DormitoryDisciplineRecordMapper, DormitoryDisciplineRecord>
    implements DormitoryDisciplineRecordService{
    private final DormitoryDisciplineRecordMapper dormitoryDisciplineMapper;
    private final DormitoryMapper dormitoryMapper;
    private final UserClient userClient;

    @Override
    public Page<DormitoryDisciplineRecord> getPageByCondition(Page<DormitoryDisciplineRecord> dormitoryDisciplineRecordPage, DormitoryDisciplineRecord condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<DormitoryDisciplineRecord> queryWrapper = new QueryWrapper<>();
        if (condition.getDormitoryId() != null) {
            queryWrapper.lambda().eq(DormitoryDisciplineRecord::getDormitoryId, condition.getDormitoryId());
        } else if(user.getRole() == 2) {
            //如果登录用户是教师角色，则只查询自己负责的宿舍相关记录
            List<Integer> dormitoryIds = dormitoryMapper.selectListByTeacherId(userId).stream()
                    .map(Dormitory::getId).distinct().collect(Collectors.toList());
            if (!dormitoryIds.isEmpty()) {
                queryWrapper.lambda().in(DormitoryDisciplineRecord::getDormitoryId, dormitoryIds);
            } else {
                return dormitoryDisciplineRecordPage;
            }
        }
        if (StrUtil.isNotBlank(condition.getIncidentType())) {
            queryWrapper.lambda().eq(DormitoryDisciplineRecord::getIncidentType, condition.getIncidentType());
        }
        queryWrapper.lambda().orderByDesc(DormitoryDisciplineRecord::getId);
        Page<DormitoryDisciplineRecord> page = dormitoryDisciplineMapper.selectPage(dormitoryDisciplineRecordPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addDormitoryDisciplineRecord(DormitoryDisciplineRecord dormitoryDiscipline) {
        dormitoryDiscipline.setIncidentDate(new Date());
        return dormitoryDisciplineMapper.insert(dormitoryDiscipline) > 0;
    }

    @Override
    public boolean deleteDormitoryDisciplineRecordById(Integer id) {
        return dormitoryDisciplineMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editDormitoryDisciplineRecordById(DormitoryDisciplineRecord dormitoryDiscipline) {
        return dormitoryDisciplineMapper.updateById(dormitoryDiscipline) > 0;
    }

    private void fillDetail(List<DormitoryDisciplineRecord> list) {
        for (DormitoryDisciplineRecord record : list) {
            Dormitory dormitory = dormitoryMapper.selectDormitoryById(record.getDormitoryId());
            record.setDormitoryName(dormitory.getDormitoryName());
        }
    }
}




