package com.myproject.boardback.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.boardback.service.FileService;

@Service
public class FileServiceImplement implements FileService {

  @Value("${file.path}")
  private String filePath;
  @Value("${file.url}")
  private String fileUrl;

  @Override
  public String upload(MultipartFile file) {
  
    if (file.isEmpty()) return null;

    String originalFileName = file.getOriginalFilename();
    String extenstion = originalFileName.substring(originalFileName.lastIndexOf("."));
    String uuid = UUID.randomUUID().toString();
    String saveFileName = uuid + extenstion;
    String savePath = filePath + saveFileName;

    try {
      file.transferTo(new File(savePath));
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }

    String url = fileUrl + saveFileName;
    return url;
  }

  @Override
  public org.springframework.core.io.Resource getImage(String fileName) {
    
    org.springframework.core.io.Resource resource = null;

    try {
      resource = new UrlResource("file:" + filePath + fileName);
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
    return resource;
  }
}
