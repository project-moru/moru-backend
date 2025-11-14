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
  public List<User> findAll() { return userRepository.findAll(); }
  
  @Override
  public Optional<User> findById(Long userId) { return userRepository.findById(userId); }
  
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }
  
  @Override
  public void deleteUserById(Long userId) { userRepository.deleteById(userId); }
  
}
