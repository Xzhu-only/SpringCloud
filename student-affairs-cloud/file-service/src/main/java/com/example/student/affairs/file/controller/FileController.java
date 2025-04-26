package com.example.student.affairs.file.controller;

import com.example.student.affairs.common.model.Result;
import com.example.student.affairs.file.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("/upload/{fileType}")
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @PathVariable("fileType") String fileType, // 传入文件类型，image 或 file
                             HttpServletRequest request) throws IOException {
        String url = fileService.uploadFile(file, fileType, request);
        // 返回文件的访问地址
        return Result.success("上传成功", url);
    }
}