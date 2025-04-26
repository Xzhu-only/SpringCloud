package com.example.student.affairs.user;

import com.example.student.affairs.config.config.MybatisPlusConfig;
import com.example.student.affairs.config.config.WebConfig;
import com.example.student.affairs.feign.client.CourseSelectionClient;
import com.example.student.affairs.feign.client.DormitoryClient;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients(clients = {DormitoryClient.class, CourseSelectionClient.class},
        defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@Import({MybatisPlusConfig.class, WebConfig.class})
@MapperScan("com.example.student.affairs.user.mapper")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}