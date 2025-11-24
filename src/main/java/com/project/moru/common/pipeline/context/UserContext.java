package com.project.moru.common.pipeline.context;

import com.project.moru.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserContext<T> {
  private final T dto;
  private User user;
}
