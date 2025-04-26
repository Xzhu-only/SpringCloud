package com.example.student.affairs.file.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传服务接口
 * @date 2024/11/15 22:19
 */
public interface FileService {
    /**
     * 文件上传服务
     * @param file 上传的文件
     * @param fileType 文件类型（image 或 file）
     * @param request HttpServletRequest 用于获取服务器的 IP 和端口
     * @return Result 上传结果
     */
    String uploadFile(MultipartFile file, String fileType, HttpServletRequest request);
}
