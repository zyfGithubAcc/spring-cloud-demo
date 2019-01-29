package com.fufu.feignHystric;

import com.fufu.feignClient.FileService;
import com.fufu.util.Result;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHystric implements FileService {
    @Override
    public Result uploadFile(MultipartFile file, String folderName, String fileName) {
        return Result.error("文件上传服务调用失败！");
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String relativePath){
        return new ResponseEntity<byte[]>(null, null, HttpStatus.NOT_FOUND);
    }

    @Override
    public Response httpGetFile(String relativePath){
        return null;
    }
}
