package com.example.student.affairs.feign.client;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.feign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Integer id);

    @GetMapping("/user/loginInfo")
    Result getLoginInfo();
}
