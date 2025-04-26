package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentHealthRecord;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class StudentHealthDTO implements DTOConvertDO<StudentHealthRecord> {
    /**
     * 主键ID
     */
    @NotNull(message = "健康记录ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 健康状况
     */
    @NotBlank(message="健康状况不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String healthStatus;

    /**
     * 体重
     */
    @NotNull(message="体重不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double weight;

    /**
     * 身高
     */
    @NotNull(message="身高不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double height;

    /**
     * 血压
     */
    @NotNull(message="血压不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double bloodPressure;

    /**
     * 疫苗接种情况（如“已接种”）
     */
    @NotBlank(message="疫苗接种情况不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String vaccinationStatus;

    /**
     * 过敏史
     */
    @NotBlank(message="过敏史不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String allergies;

    /**
     * 慢性病史
     */
    @NotBlank(message="慢性病史不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String chronicConditions;

    @Override
    public StudentHealthRecord dtoToDo() {
        return StudentHealthRecord.builder()
                .id(id)
                .studentId(studentId)
                .healthStatus(healthStatus)
                .weight(weight)
                .height(height)
                .bloodPressure(bloodPressure)
                .vaccinationStatus(vaccinationStatus)
                .allergies(allergies)
                .chronicConditions(chronicConditions)
                .build();
    }
}
