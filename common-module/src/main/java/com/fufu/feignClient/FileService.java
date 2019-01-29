package com.fufu.feignClient;

import com.fufu.feignHystric.FileHystric;
import com.fufu.util.Result;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "file-service",fallback = FileHystric.class)
public interface FileService {

    @PostMapping(value = "/uploadFile")
    public Result uploadFile(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "folderName") String folderName,
            @RequestParam(value = "fileName") String fileName);

    @GetMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(
            @RequestParam(value = "relativePath") String relativePath);

    @GetMapping("/httpGetFile")
    public Response httpGetFile(
            @RequestParam(value = "relativePath") String relativePath);

}
