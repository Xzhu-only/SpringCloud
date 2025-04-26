package com.example.student.affairs.common.model;

/**
 * DO实体对象转化VO前端对象接口
 */
public interface DOConvertVO<T> {
    /**
     * 提供DTO参数对象转化成domain实体对象的方法
     * @return  T
     */
    T doToVo();
}
