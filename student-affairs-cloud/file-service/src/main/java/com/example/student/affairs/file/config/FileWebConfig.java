package com.example.student.affairs.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web配置
 */
@Configuration
public class FileWebConfig implements WebMvcConfigurer {
    @Resource
    StudentAffairsConfig studentAffairsConfig;

    /**
     * 重新配置静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置上传文件的静态资源路径
        registry.addResourceHandler(studentAffairsConfig.getUpload().getAccessPath()) // 配置访问路径
                .addResourceLocations("file:" + studentAffairsConfig.getUpload().getPath()); // 配置文件存放的物理路径
    }
}
