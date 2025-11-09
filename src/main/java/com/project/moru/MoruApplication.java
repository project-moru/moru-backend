package com.project.moru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoruApplication {
  public static void main(String[] args) {
    SpringApplication.run(MoruApplication.class, args);
    System.out.println("MoruApplication started");
  }
}