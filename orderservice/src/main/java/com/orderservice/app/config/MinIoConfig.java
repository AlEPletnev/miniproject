package com.orderservice.app.config;

import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("minioconfig.properties")
public class MinIoConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.login}")
    private String login;

    @Value("${minio.password}")
    private String password;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(login,password)
                .build();
    }

}
