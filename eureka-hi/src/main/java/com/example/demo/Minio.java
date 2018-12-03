package com.example.demo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Item;

public class Minio {
	
	//服务器地址
	  private static String endpoint = "http://127.0.0.1:9000/";

	  // 用户名
      private static String accessKey = "stronger0724";

      // 密码
      private static String secretKey = "lq123456";

      // 创建的文件名
      private static String bucketName = "files4";



      public static void main(String[] args)

                    throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {

             try {

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


                    //C:\Users\Pactera\Desktop

                  //  minioClient.putObject(bucketName, "hyrz.png", "C:\\Users\\Pactera\\Desktop\\hyrz.png");

                    // minioClient.putObject(bucketName, objectName, fileName);

                /*    Iterable<Result<Item>> listObjects = minioClient.listObjects(bucketName);

                    for (Result<Item> result : listObjects) {

                           Item item = result.get();

                           System.out.println(item.objectName());
                           
                           // minioClient.removeObject(bucketName, item.objectName());

                    }*/
                   //  String objectUrl = minioClient.getObjectUrl(bucketName, "hyrz.png");
                    // String presignedGetObject = minioClient.presignedGetObject(objectUrl, "hyrz.png");
                     
                     String presignedPutObject = minioClient.presignedPutObject(bucketName, "hyrz.png", 60 * 60 * 24);
                     System.out.println("presignedPutObject:"+presignedPutObject);
                     
                  /*   
                     // 获取文件的访问路径
                     String presignedGetObject = minioClient.presignedGetObject(bucketName, "hyrz.png");
                     *//**
                      * bucketName Bucket name.（Bucket名称）
                      * objectName Object name in the bucket.（桶中的对象名）
                      * expires Expiration time in seconds of presigned URL.（失效时间（以秒为单位），默认是7天，不得大于七天。）
                      * 
                      * 
                      *//*
                     String presignedGetObject2 = minioClient.presignedGetObject(bucketName, "hyrz.png", 60 * 60 * 24);
                     *//**
                      * 
                      * bucketName Bucket name.
                      * objectName Object name in the bucket.
                      * expires Expiration time in seconds of presigned URL.
                      * reqParams Override values for set of response headers. Currently supported request parameters are [response-expires, response-content-type, response-cache-control, response-content-disposition]
                      * (覆盖响应标头集的值。当前支持的请求参数为[response-expires, response-content-type, response-cache-control, response-content- dispose])
                      *//*
                     Map<String, String> reqParams = new HashMap<String, String>();
					 String presignedGetObject3 = minioClient.presignedGetObject(bucketName,  "hyrz.png", 60 * 60 * 24, reqParams );
                    System.out.println("url: "+presignedGetObject);
                    System.out.println("url2: "+presignedGetObject2);
                    System.out.println("url3: "+presignedGetObject3);*/
                   // ObjectStat stat= minioClient.statObject(bucketName,"hyrz.png");
                    //System.out.println(stat);



                   /* System.out.println(

                                  "/home/user/Photos/asiaphotos.zip is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
*/
             } catch (MinioException e) {

                    System.out.println("Error occurred: " + e);

             }

      }


}
