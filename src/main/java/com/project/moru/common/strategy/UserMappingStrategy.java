package com.project.moru.common.strategy;

import com.project.moru.user.domain.entity.User;

public interface UserMappingStrategy<T> {
  User map(T dto, User user);
}