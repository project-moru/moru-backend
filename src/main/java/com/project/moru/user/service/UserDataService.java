package com.project.moru.user.service;

import com.project.moru.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDataService {
  List<User> findAllUsers();
  
  Optional<User> findUserById(Long userId);
  
  Optional<User> findUserByUsername(String username);
  
  Optional<User> findUserByNickname(String nickname);
  
  void saveUser(User user);
  
  void deleteUserById(Long userId);
}
