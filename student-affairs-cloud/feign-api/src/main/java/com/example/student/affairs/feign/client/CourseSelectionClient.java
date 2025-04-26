package com.example.student.affairs.feign.client;

import com.example.student.affairs.feign.model.CourseSelection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @date 2024/12/21 6:43
 */
@FeignClient("course-service")
public interface CourseSelectionClient {
    @GetMapping("/course/selection/list")
    List<CourseSelection> getList();
}
