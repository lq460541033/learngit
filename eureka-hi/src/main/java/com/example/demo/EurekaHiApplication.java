package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class EurekaHiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(EurekaHiApplication.class, args);
	}
	
	//服务器地址
	@Value("${minio.endpoint}")
	  private String endpoint;
	  // 用户名
	 @Value("${minio.accessKey}")
    private  String accessKey;
    // 密码
	@Value("${minio.secretKey}")
    private  String secretKey;
    // 密码
	@Value("${minio.bucketName}")
    private  String bucketName;
	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}
	
	@RequestMapping("uploadImgAlbums")
	public void albumUploadImgs(@RequestParam(value = "files", required = true) MultipartFile[] multipartFiles,HttpServletRequest req, HttpServletResponse response) throws IOException{
	   	try {
			 //  MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
			   
			   MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);

	           
	           // Check if the bucket already exists.（检查桶是否已经存在。）
	           boolean isExist = minioClient.bucketExists(bucketName);
	           if (isExist) {
	                  System.out.println("Bucket already exists.");
	           } else {
	                  // Make a new bucket called asiatrip to hold a zip file of photos.
	           	      //制作一个名为bucketName的新木桶，用来存放zip文件的照片
	                  minioClient.makeBucket(bucketName);
	           }
		
		      for (MultipartFile multipartFile : multipartFiles) {
		    	  
	        	   ByteArrayInputStream bais = new  ByteArrayInputStream(multipartFile.getBytes());
	        	   // Create an object
	        	   minioClient.putObject(bucketName, multipartFile.getOriginalFilename(), bais, bais.available(), multipartFile.getContentType());
	        	   bais.close();
	        	   System.out.println("myobject is uploaded successfully");    
			}
	      } catch(Exception e) {
       	   System.out.println("Error occurred: " + e);
       	 }
          
	 }
	
	
	
	@RequestMapping("/get")
	public String presignedGetObject(String Filename ) {
			  MinioClient minioClient;
		try {
			minioClient = new MinioClient(endpoint, accessKey, secretKey);
			 // 获取文件的访问路径
	        String presignedGetObject = minioClient.presignedGetObject(bucketName, Filename);
	        return presignedGetObject;
			
		} catch(Exception e) {
			  System.out.println("Error occurred: " + e);
			  e.printStackTrace();
			  return "Error occurred: 报错 ";
		}
	}
	
	
	
	
}
