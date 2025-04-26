package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.StudentRegistration;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 21:27
 */
@Data
public class StudentRegistrationDTO implements DTOConvertDO<StudentRegistration> {
    /**
     * 主键ID
     */
    @NotNull(message = "主键ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 新生姓名
     */
    @NotBlank(message="新生姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String studentName;

    /**
     * 新生学号
     */
    @NotBlank(message="新生学号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String studentId;

    /**
     * 性别
     */
    @NotBlank(message="性别不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String gender;

    /**
     * 手机号
     */
    @NotBlank(message="手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String phoneNumber;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 状态，未签到、已签到、已取消
     */
    @NotBlank(message="状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String state;

    /**
     * 用户ID
     */
    @NotNull(message="用户ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer userId;

    /**
     * 备注
     */
    private String remark;

    @Override
    public StudentRegistration dtoToDo() {
        return StudentRegistration.builder()
                .id(id)
                .studentName(studentName)
                .studentId(studentId)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .email(email)
                .state(state)
                .userId(userId)
                .remark(remark)
                .build();
    }
}
