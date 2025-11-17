
package com.project.moru.common.constant;

import lombok.Getter;

@Getter
public enum Use {
  Y("활성화"),
  N("비활성화");
  
  private final String description;
  
  Use(String description) {
    this.description = description;
  }
}