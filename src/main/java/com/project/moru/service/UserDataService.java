package com.project.moru.service;

import com.project.moru.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDataService {
  List<User> findAllUsers();
  
  Optional<User> findUserById(Long userId);
  
  User saveUser(User user);
  
  void deleteUserById(Long userId);
}
