package com.example.student.affairs.dormitory.model.dto;

import com.example.student.affairs.common.model.DTOConvertDO;
import com.example.student.affairs.common.valid.AddGroup;
import com.example.student.affairs.common.valid.UpdateGroup;
import com.example.student.affairs.dormitory.model.DormitoryStayRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @date 2024/12/19 22:06
 */
@Data
public class DormitoryStayDTO implements DTOConvertDO<DormitoryStayRequest> {
    /**
     * 主键ID
     */
    @NotNull(message = "申请ID修改时不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 学生ID
     */
    @NotNull(message="学生ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer studentId;

    /**
     * 宿舍ID
     */
    @NotNull(message="宿舍ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dormitoryId;

    /**
     * 留校起始日期
     */
    @NotNull(message="留校起始日期不能为空", groups = {AddGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date stayPeriodStart;

    /**
     * 留校结束日期
     */
    @NotNull(message="留校结束日期不能为空", groups = {AddGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date stayPeriodEnd;

    /**
     * 留校原因
     */
    @NotBlank(message="留校原因不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String reason;

    /**
     * 审批状态（待审批, 批准, 拒绝）
     */
    @NotBlank(message="审批状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String approvalStatus;

    @Override
    public DormitoryStayRequest dtoToDo() {
        return DormitoryStayRequest.builder()
                .id(id)
                .studentId(studentId)
                .dormitoryId(dormitoryId)
                .stayPeriodStart(stayPeriodStart)
                .stayPeriodEnd(stayPeriodEnd)
                .reason(reason)
                .approvalStatus(approvalStatus)
                .build();
    }
}
