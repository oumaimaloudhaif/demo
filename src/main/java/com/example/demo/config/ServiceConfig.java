package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
public class ServiceConfig {
    @Value("${spring.projectName}")
    private String projectName;

    public String getProjectName(){
        return this.projectName;
    }

    public  void setProjectName(String example){
        this.projectName=example;
    }
}
