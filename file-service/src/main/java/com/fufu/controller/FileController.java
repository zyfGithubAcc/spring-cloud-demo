package com.fufu.controller;

import com.fufu.service.FileService;
import com.fufu.util.FileTypeUtil;
import com.fufu.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class FileController {

    @Value("${upload.rootPath}")
    private String rootPath;
    @Autowired
    private FileService fileService;

    /**
     * 单个文件上传
     */
    @ApiOperation(value="单个文件上传")
    @PostMapping(value="/uploadFile")
    public Result uploadFile(MultipartFile file, String folderName, String fileName) {
        //获取文件后缀名
        String ext= FileTypeUtil.getFileSuffix(file.getOriginalFilename());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        try {
            String filePath = folderName +"/" + sf.format(new Date()) +"/" ;
            String finalName = new Random().nextInt(999999) + "."+ ext;

            String finalPath = rootPath + filePath;
            if(fileName != null) {
                finalName = fileName +"_" + finalName ;
            }
            String fullPath = fileService.uploadFile(file.getBytes(), finalPath, finalName);
            if(fullPath.isEmpty()) {
                return null;
            }
            return Result.ok("上传成功,文件保存路径为:"+filePath + finalName);

        }catch (Exception e) {
            e.printStackTrace();
            return Result.error("uploadimg failure: " + e.getMessage());
        }
    }

    /**
     * 文件（二进制数据）下载
     */
    @GetMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String relativePath, HttpServletRequest request ){
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> entity = null;
        InputStream in=null;
        try {
            String fullPath = rootPath + relativePath;
            in=new FileInputStream(new File(fullPath));
            byte[] bytes = new byte[in.available()];
            String[] pathArr = relativePath.split("/");
            Integer splitNum = pathArr.length-1;
            String imageName = pathArr[splitNum];
            //处理IE下载文件的中文名称乱码的问题
            String header = request.getHeader("User-Agent").toUpperCase();
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                imageName = URLEncoder.encode(imageName, "utf-8");
                imageName = imageName.replace("+", "%20");    //IE下载文件名空格变+号问题
            } else {
                imageName = new String(imageName.getBytes(), "iso-8859-1");
            }
            in.read(bytes);
            headers.add("Content-Disposition", "attachment;filename="+imageName);
            entity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    /**
     * http获取文件
     */
    @GetMapping("/httpGetFile")
    public void httpGetFile(String relativePath, HttpServletRequest request, HttpServletResponse response){
        if (relativePath != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
//            String realPath = request.getServletContext().getRealPath("//WEB-INF//");
            String fullPath = rootPath + relativePath;
            File file = new File(fullPath);
            if (file.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("file download success! ");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
