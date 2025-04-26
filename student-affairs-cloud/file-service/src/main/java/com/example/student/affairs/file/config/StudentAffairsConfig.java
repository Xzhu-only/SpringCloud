package com.example.student.affairs.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目自定义配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "student-affairs")
public class StudentAffairsConfig {
    // 文件上传配置
    private Upload upload;

    @Data
    public static class Upload {
        // 文件上传的真实地址
        private String path;

        // 上传文件的访问路径（例如：/upload/**）
        private String accessPath;
    }
}
