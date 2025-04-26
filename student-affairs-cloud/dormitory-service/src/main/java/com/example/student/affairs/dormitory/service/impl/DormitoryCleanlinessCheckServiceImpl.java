package com.example.student.affairs.dormitory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import com.example.student.affairs.dormitory.mapper.DormitoryMapper;
import com.example.student.affairs.dormitory.model.Dormitory;
import com.example.student.affairs.dormitory.model.DormitoryCleanlinessCheck;
import com.example.student.affairs.dormitory.service.DormitoryCleanlinessCheckService;
import com.example.student.affairs.dormitory.mapper.DormitoryCleanlinessCheckMapper;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【dormitory_cleanliness_check(宿舍卫生检查表)】的数据库操作Service实现
* @createDate 2024-12-19 19:03:07
*/
@Service
@RequiredArgsConstructor
public class DormitoryCleanlinessCheckServiceImpl extends ServiceImpl<DormitoryCleanlinessCheckMapper, DormitoryCleanlinessCheck>
    implements DormitoryCleanlinessCheckService{
    private final DormitoryCleanlinessCheckMapper dormitoryCleanlinessMapper;
    private final DormitoryMapper dormitoryMapper;
    private final UserClient userClient;

    @Override
    public Page<DormitoryCleanlinessCheck> getPageByCondition(Page<DormitoryCleanlinessCheck> dormitoryCleanlinessCheckPage,
                                                              DormitoryCleanlinessCheck condition) {
        Integer userId = ThreadLocalUtil.get();
        User user = userClient.getUserById(userId);
        QueryWrapper<DormitoryCleanlinessCheck> queryWrapper = new QueryWrapper<>();
        if (condition.getDormitoryId() != null) {
            queryWrapper.lambda().eq(DormitoryCleanlinessCheck::getDormitoryId, condition.getDormitoryId());
        } else if(user.getRole() == 2) {
            //如果登录用户是教师角色，则只查询自己负责的宿舍相关记录
            List<Integer> dormitoryIds = dormitoryMapper.selectListByTeacherId(userId).stream()
                    .map(Dormitory::getId).distinct().collect(Collectors.toList());
            if (!dormitoryIds.isEmpty()) {
                queryWrapper.lambda().in(DormitoryCleanlinessCheck::getDormitoryId, dormitoryIds);
            } else {
                return dormitoryCleanlinessCheckPage;
            }
        }
        if (StrUtil.isNotBlank(condition.getCleanlinessStatus())) {
            queryWrapper.lambda().eq(DormitoryCleanlinessCheck::getCleanlinessStatus, condition.getCleanlinessStatus());
        }
        queryWrapper.lambda().orderByDesc(DormitoryCleanlinessCheck::getId);
        Page<DormitoryCleanlinessCheck> page = dormitoryCleanlinessMapper.selectPage(dormitoryCleanlinessCheckPage, queryWrapper);
        this.fillDetail(page.getRecords());
        return page;
    }

    @Override
    public boolean addDormitoryCleanlinessCheck(DormitoryCleanlinessCheck dormitoryCleanliness) {
        dormitoryCleanliness.setCheckDate(new Date());
        return dormitoryCleanlinessMapper.insert(dormitoryCleanliness) > 0;
    }

    @Override
    public boolean deleteDormitoryCleanlinessCheckById(Integer id) {
        return dormitoryCleanlinessMapper.deleteById(id) > 0;
    }

    @Override
    public boolean editDormitoryCleanlinessCheckById(DormitoryCleanlinessCheck dormitoryCleanliness) {
        return dormitoryCleanlinessMapper.updateById(dormitoryCleanliness) > 0;
    }

    private void fillDetail(List<DormitoryCleanlinessCheck> list) {
        for (DormitoryCleanlinessCheck check : list) {
            Dormitory dormitory = dormitoryMapper.selectDormitoryById(check.getDormitoryId());
            check.setDormitoryName(dormitory.getDormitoryName());
        }

    }
}




