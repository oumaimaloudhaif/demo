package com.example.demo.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component

public class SecretConfig {
    public static void loadEnv(){
       final Dotenv dotenv = Dotenv.configure().load();

       // Get Secrets Values
       final String BD_URL=dotenv.get("BD_URL");
       final String BD_USER_NAME=dotenv.get("BD_USER_NAME");
       final String BD_PWD=dotenv.get("BD_PWD");

       // Set Secrets in spring Context
        System.setProperty("BD_URL",BD_URL);
        System.setProperty("BD_USER_NAME",BD_USER_NAME);
        System.setProperty("BD_PWD",BD_PWD);
    }
}
