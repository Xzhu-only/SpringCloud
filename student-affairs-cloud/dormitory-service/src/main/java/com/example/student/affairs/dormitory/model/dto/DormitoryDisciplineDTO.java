package com.example.student.affairs.dormitory.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryDisciplineRecord;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/19 22:06
 */
@Data
public class DormitoryDisciplineDTO implements DTOConvertDO<DormitoryDisciplineRecord> {
    /**
     * 主键ID
     */
    @NotNull(message = "违纪记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 宿舍ID
     */
    @NotNull(message="宿舍ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dormitoryId;

    /**
     * 违纪类型
     */
    @NotBlank(message="违纪类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String incidentType;

    /**
     * 违纪详情
     */
    @NotBlank(message="违纪详情不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String incidentDetails;

    /**
     * 处罚措施
     */
    @NotBlank(message="处罚措施不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String punishment;

    @Override
    public DormitoryDisciplineRecord dtoToDo() {
        return DormitoryDisciplineRecord.builder()
                .id(id)
                .dormitoryId(dormitoryId)
                .incidentType(incidentType)
                .incidentDetails(incidentDetails)
                .punishment(punishment)
                .build();
    }
}
