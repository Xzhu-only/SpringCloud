package com.example.student.affairs.user.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.user.model.CampusCard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @date 2024/12/20 1:53
 */
@Data
public class CampusCardDTO implements DTOConvertDO<CampusCard> {
    /**
     * 主键ID
     */
    @NotNull(message = "校园卡ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 校园卡余额
     */
    @NotNull(message="校园卡余额不能为空", groups = {UpdateGroup.class})
    private Double balance;

    /**
     * 校园卡状态
     */
    @NotBlank(message="校园卡状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String state;

    /**
     * 所属学生ID
     */
    @NotNull(message="所属学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    @Override
    public CampusCard dtoToDo() {
        return CampusCard.builder()
                .id(id)
                .balance(balance)
                .state(state)
                .studentId(studentId)
                .build();
    }
}
