package com.orderservice.app.dao;

import java.io.InputStream;

public interface S3Storage {

    void uploadFile(String bucketName, String fileName, byte[] pdfFile);

    InputStream download(String bucketName, String fileName);

    void delete(String bucketName, String fileName);
}
