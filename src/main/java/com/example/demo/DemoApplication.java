package com.example.demo;

import com.example.demo.config.SecretConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SecretConfig.loadEnv();
    SpringApplication.run(DemoApplication.class, args);
  }
}
