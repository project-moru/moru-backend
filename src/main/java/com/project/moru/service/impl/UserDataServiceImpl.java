package com.project.moru.service.impl;

import com.project.moru.domain.entity.user.User;
import com.project.moru.repository.UserRepository;
import com.project.moru.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {
  
  private final UserRepository userRepository;
  
  @Override
  public List<User> findAllUsers() { return userRepository.findAll(); }
  
  @Override
  public Optional<User> findUserById(Long userId) { return userRepository.findById(userId); }
  
  @Override
  public Optional<User> findUserByUsername(String username) { return userRepository.findByUsername(username); }
  
  @Override
  public Optional<User> findUserByNickname(String nickname) { return userRepository.findByNickname(nickname); }
  
  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }
  
  @Override
  public void deleteUserById(Long userId) { userRepository.deleteById(userId); }
  
}
