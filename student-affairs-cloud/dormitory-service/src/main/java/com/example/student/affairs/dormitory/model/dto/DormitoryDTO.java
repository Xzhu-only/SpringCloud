package com.example.student.affairs.dormitory.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.Dormitory;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 22:06
 */
@Data
public class DormitoryDTO implements DTOConvertDO<Dormitory> {
    /**
     * 主键ID
     */
    @NotNull(message = "宿舍ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 宿舍名称
     */
    @NotBlank(message="宿舍名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dormitoryName;

    /**
     * 宿舍楼名称
     */
    @NotBlank(message="宿舍楼名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String buildingName;

    /**
     * 宿舍最大容纳人数
     */
    @NotNull(message="宿舍最大容纳人数不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer capacity;

    /**
     * 当前已入住人数
     */
    private Integer currentOccupancy;

    /**
     * 负责老师ID
     */
    @NotNull(message="负责老师ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer teacherId;

    @Override
    public Dormitory dtoToDo() {
        return Dormitory.builder()
                .id(id)
                .dormitoryName(dormitoryName)
                .buildingName(buildingName)
                .capacity(capacity)
                .currentOccupancy(currentOccupancy)
                .teacherId(teacherId)
                .build();
    }
}
