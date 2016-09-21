package com.school.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.school.bean.account.ImgPath;
import com.school.common.BaseConteroller;
import com.school.form.AjaxForm;
import com.school.service.ImgService;

@Controller
@RequestMapping("/upload")
public class UploadService extends BaseConteroller{
	
	List<String> fileTypes = Arrays.asList("jpg", "png", "bmp","gif");
	
	private static long fileSize=50000;//kb
	
	@Resource
	private ImgService imgService;
	
	//上传图片
	@RequestMapping("/uploadImg")
	private void uploadImg(@RequestParam("file")MultipartFile file){
		String filePath;
		//AjaxForm ajaxForm=new AjaxForm();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("ret",1);
		  String fileName = file.getOriginalFilename();  
	        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
	         String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
	         //对扩展名进行小写转换  
	         ext = ext.toLowerCase(); 
	         if(fileTypes.contains(ext)) { //如果扩展名属于允许上传的类型，则创建文件  
	        	 //判断大小
	             if(fileSize>=file.getSize()){
	            	 try {
	            		 String newName=UUIDutil.createUuid();
	 	     			filePath = FileUpload.uploadFile(file, request,ext,newName);
	 	     			ImgPath imgPath=new ImgPath(fileName, filePath, ext,newName);
	 	     			imgService.save(imgPath);
	 	     			resultMap.put("ret",0);
	 	     			resultMap.put("msg",filePath);
	 	     			resultMap.put("name",newName);
	 	     		       // response.setContentType("text/html;charset=utf8");  
	 	     		       // response.getWriter().write(filePath); 
	 	     		} catch (IOException e) {
	 	     			e.printStackTrace();
	 	     			resultMap.put("msg","操作失败:"+e.getMessage());
	 	     			
	 	     		} 
	             }else{
	            	 resultMap.put("msg","操作失败:文件过大不得超过50kb");
	             }
	        	 
	         }else{
	        	 resultMap.put("msg","图片无效格式!"); 
	         }
	         out.print(JsonUtil.createJsonString(resultMap));
	
}	
	
	@RequestMapping("/download")  
    public void download(String fileName) throws IOException {  
		response.reset(); 
		OutputStream os = response.getOutputStream();  
        try {  
             
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            response.setContentType("image/jpeg; charset=utf-8");  
            os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));  
            os.flush();  
        } finally {  
            if (os != null) {  
                os.close();  
            }  
        }  
    }  
	
	
	/**
	 * 删除图片
	 * @param name
	 * @return
	 */
	@RequestMapping("/deleteImg")
	private void deleteImg(String name){
		AjaxForm ajaxForm=new AjaxForm();
		String fileNamepath=imgService.findByNewNmae(name);
		int count=imgService.deleteByNewNmae(name);
		FileUpload.deleteFile(request, fileNamepath);
		ajaxForm.setMsg("删除"+count+"张");
		 out.print(JsonUtil.createJsonString(ajaxForm));
	}

	
}
