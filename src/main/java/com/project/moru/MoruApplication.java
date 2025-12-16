package com.project.moru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoruApplication {
  public static void main(String[] args) {
    SpringApplication.run(MoruApplication.class, args);
    System.out.println("MoruApplication started");
  }
}