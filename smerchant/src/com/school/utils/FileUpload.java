package com.school.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {  
  
    public static final String FILE_PATH = "/upload/";  
  
    /**
     * 上传，返回新文件名  
     * @param file
     * @param request
     * @param ext
     * @param newName
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request,String ext,String newName) throws IOException {  
        //String fileName = file.getOriginalFilename(); 
    	System.out.println(request.getContextPath()+FILE_PATH);
    	System.out.println(request.getRealPath("/"));
        File tempFile = new File(request.getRealPath("")+FILE_PATH,newName+"."+ext);  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdirs();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return FILE_PATH+tempFile.getName();  
    }  
  
    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static int deleteFile(HttpServletRequest request,String fileNamepath) { 
    	 File tempFile = new File(request.getRealPath("")+fileNamepath);  
         if (tempFile.exists()) {  //存在则创建
             tempFile.delete();  
         }else{
        	 return 1;   
         }
        return 0;  
    } 
    public static File getFile(String fileName) {  
        return new File(FILE_PATH, fileName);  
    }  
}  