package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component

public class SecretConfig {
    public static void loadEnv(){
        // Get Secrets Values from Environment Variables
        final String BD_URL = System.getenv("BD_URL");
        final String BD_USER_NAME = System.getenv("BD_USER_NAME");
        final String BD_PWD = System.getenv("BD_PWD");

        // Set Secrets in Spring Context
        System.setProperty("BD_URL", BD_URL);
        System.setProperty("BD_USER_NAME", BD_USER_NAME);
        System.setProperty("BD_PWD", BD_PWD);
    }
}
