package com.project.moru.service;

import com.project.moru.domain.entity.user.User;

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
