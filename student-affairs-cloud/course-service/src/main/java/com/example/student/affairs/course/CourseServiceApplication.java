package com.example.student.affairs.course;

import com.example.student.affairs.config.config.MybatisPlusConfig;
import com.example.student.affairs.config.config.WebConfig;
import com.example.student.affairs.feign.client.UserClient;
import com.example.student.affairs.feign.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients(clients = {UserClient.class}, defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@Import({MybatisPlusConfig.class, WebConfig.class})
@MapperScan("com.example.student.affairs.course.mapper")
public class CourseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }
}