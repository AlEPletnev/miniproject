package com.orderservice.app.dao;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class MinIOStorage implements S3Storage{

    private final MinioClient minioClient;

    @Autowired
    public MinIOStorage(MinioClient minioClient){
        this.minioClient = minioClient;
    }

    @Override
    public void uploadFile(String bucketName, String fileName, byte[] pdfFile){
        boolean isExist = false;
        try {
            isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!isExist){
            System.err.println("Бакет не существует");
            return;
        }

        try(ByteArrayInputStream streambyte = new ByteArrayInputStream(pdfFile)){
            minioClient.putObject(PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(streambyte,pdfFile.length,-1)
                            .contentType("application/pdf")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream download(String bucketName, String fileName){
        boolean isExist = false;
        try{
            isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!isExist){
            System.err.println("Buket not found");
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    @Override
    public void delete(String bucketName, String fileName){
        boolean fileExists = false;
        try {
            fileExists = minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build()) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(fileExists){
            try {
                minioClient.removeObject(RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
