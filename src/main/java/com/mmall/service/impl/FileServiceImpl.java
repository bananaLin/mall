package com.mmall.service.impl;

import com.mmall.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 上传文件
     * @param multipartFile
     * @param path
     * @return
     */
    @Override
    public String upload(MultipartFile multipartFile, String path) {
        String fileName = multipartFile.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("[上传文件] 文件名：{},上传路径：{}, 新文件名:{}", fileName, path, uploadFileName);
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try{
            multipartFile.transferTo(targetFile);

        }catch(IOException e){
            logger.error("[上传文件异常]", e);
            return null;
        }
        return targetFile.getName();
    }
}
