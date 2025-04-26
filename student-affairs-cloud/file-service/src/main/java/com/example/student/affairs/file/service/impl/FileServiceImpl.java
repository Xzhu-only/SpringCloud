package com.example.student.affairs.file.service.impl;

import com.example.student.affairs.common.exception.BusinessException;
import com.example.student.affairs.file.config.StudentAffairsConfig;
import com.example.student.affairs.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @date 2024/11/15 22:20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
    private final StudentAffairsConfig studentAffairsConfig;

    @Override
    public String uploadFile(MultipartFile file, String fileType, HttpServletRequest request) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new BusinessException("文件名为空");
        }

        // 获取文件扩展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newFileName = System.currentTimeMillis() + "." + extendName;  // 使用当前时间戳生成文件名

        // 获取上传路径和文件存储目录
        String uploadPath = studentAffairsConfig.getUpload().getPath();
        String fileDir = uploadPath + File.separator + fileType;

        // 创建目录，如果目录不存在则创建
        File dir = new File(fileDir);
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdirs();
            if (!dirCreated) {
                throw new BusinessException("目录创建失败，请检查文件路径权限！");
            }
        }

        // 上传文件
        File destFile = new File(fileDir + File.separator + newFileName);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new BusinessException("文件上传操作失败！");
        }


        // 获取项目运行的 IP 和端口
//        String ipAddress = request.getServerName();
        String ipAddress = "localhost";
        int port = request.getServerPort();
        String accessPath = studentAffairsConfig.getUpload().getAccessPath().replace("/**", "");  // 去掉 /**

        // 根据文件类型拼接返回的 URL

        return "http://" + ipAddress + ":" + port + accessPath + "/" + fileType + "/" + newFileName;
    }
}
