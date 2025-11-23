package com.project.moru.common.strategy;

import com.project.moru.domain.entity.user.User;

public interface UserMappingStrategy<T> {
  User map(T dto, User user);
}