package com.fufu.controller;

import com.fufu.feignClient.FileService;
import com.fufu.util.Result;
import feign.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RefreshScope
@RestController
    @RequestMapping("/file")
public class FileController {

//    @Value("${test}")
//    private String test;

    @Autowired FileService fileService;

    @ApiOperation(value="调用file-service进行文件上传")
    @PostMapping(value = "/uploadFile")
    public Result uploadFile(MultipartFile file, String folderName, String fileName) {
        return fileService.uploadFile(file, folderName, fileName);
    }

    @GetMapping("/downloadFile")
    public Object downloadFile(String relativePath) {
        ResponseEntity<byte[]> entity = fileService.downloadFile(relativePath);
//        System.out.println( entity.getStatusCode());
        return entity;
    }

    @GetMapping("/httpGetFile")
    public void httpGetFile(String relativePath, HttpServletResponse response){
        Response feignResponse = fileService.httpGetFile(relativePath);
        byte[] buffer = new byte[1024];
        try {
            InputStream inputStream = feignResponse.body().asInputStream();
            OutputStream os = response.getOutputStream();
            int i = inputStream.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
