package com.example.student.affairs.feign.client;

import com.example.student.affairs.feign.model.Dormitory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("dormitory-service")
public interface DormitoryClient {
    @GetMapping("/dormitory/{id}")
    Dormitory getDormitoryById(@PathVariable("id")Integer id);

    @PostMapping("/dormitory/update")
    boolean updateDormitoryDeign(@RequestBody Dormitory dormitory);
}
