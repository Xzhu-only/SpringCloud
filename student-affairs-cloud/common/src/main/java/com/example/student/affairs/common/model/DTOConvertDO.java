package com.example.student.affairs.common.model;

/**
 * DTO参数对象转化domain对象接口
 */
public interface DTOConvertDO<T> {
    /**
     * 提供DTO参数对象转化成domain实体对象的方法
     * @return  T
     */
    T dtoToDo();
}
