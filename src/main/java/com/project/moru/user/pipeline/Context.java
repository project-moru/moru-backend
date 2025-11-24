package com.project.moru.user.pipeline;

import com.project.moru.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Context<T> {
  private final T dto;
  private User user;
}
