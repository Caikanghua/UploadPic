package com.readbook.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.readbook.service.RedisService;
import com.readbook.utils.IDUtils;

@Controller
public class UploadPicController {
	@Autowired
	private RedisService redisService;
	@RequestMapping("/uploadPicture")
	@ResponseBody
	public String uploadPicture(@RequestParam MultipartFile image,String company,HttpServletRequest request) {
		//处理图片
		//获取用户id
		String fileName="";
		boolean empty = image.isEmpty();
		//判断用户是否上传照片
		if(!empty){
			String path = request.getSession().getServletContext().getRealPath("/upload/image/finance");  
			fileName= image.getOriginalFilename();   
	        fileName=IDUtils.genImageName()+fileName.substring(fileName.lastIndexOf('.'));
	        File dir = new File(path,fileName);  
	        // 如果存储图片路径不存在，就创建一个
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }
	        try {
				image.transferTo(dir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //MultipartFile自带的解析方法  
		//上传成功，将图片路径插入到数据库中
    	if(!"".equals(fileName)){
    		String imaUrl = "https://caikanghua.top/finance/upload/image/finance/"+fileName;
    		redisService.addPicUrlMapper(company, imaUrl);
    		return "上传成功!";
    	} else {
    		return "上传失败";
    	}
	}
	/**
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getPicture")
	public ModelAndView getPicUrl(String company,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");   
        company = new String(company.getBytes("ISO-8859-1"),"utf-8");
        String picUrl = redisService.getPicUrl(company);
		ModelAndView mv = new ModelAndView();
		mv.addObject("picUrl", picUrl);
		mv.setViewName("index");
		return mv;
	}
	
	
	
}
