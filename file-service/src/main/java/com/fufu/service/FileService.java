package com.fufu.service;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileService {

	public String uploadFile(byte[] file, String finalPath, String finalName)throws IOException{
        File targetFile = new File(finalPath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }
        FileOutputStream out;
		out = new FileOutputStream(finalPath+finalName);
		out.write(file);
		out.flush();
		out.close();
//		System.out.println(finalPath + finalName);
		return finalPath + finalName;
    }

}

