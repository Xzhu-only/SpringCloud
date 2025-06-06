package com.example.student.affairs.gateway.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "student-affairs.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
