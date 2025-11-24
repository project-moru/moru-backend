package com.project.moru.user.constant;

import lombok.Getter;

@Getter
public enum Job {
  DESIGNER("디자이너"),
  RESEARCHER("연구원");
  
  private final String description;
  
  Job(String description) {
    this.description = description;
  }
}