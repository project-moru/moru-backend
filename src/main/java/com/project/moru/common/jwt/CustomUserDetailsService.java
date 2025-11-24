package com.project.moru.common.jwt;

import com.project.moru.domain.entity.user.CustomUserDetails;
import com.project.moru.domain.entity.user.User;
import com.project.moru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    
    return new CustomUserDetails(user);
  }
  
  public UserDetails loadUserById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    
    return new CustomUserDetails(user);
  }
}
