package com.example.demo.controllers;


import com.example.demo.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @Autowired
    private ServiceConfig serviceConfig;

   @GetMapping("/healthCheck")
   public String getHealthCheckMsg() {
       return serviceConfig.getProjectName();
   }
}
