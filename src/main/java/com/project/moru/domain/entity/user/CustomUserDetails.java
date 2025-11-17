package com.project.moru.domain.entity.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {
  private final String username; // 아이디
  private final String nickname;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;
  
  public CustomUserDetails(User user) {
    this.username = user.getUsername();
    this.nickname = user.getNickname();
    this.password = user.getPassword();
    this.authorities = Collections.emptyList();
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  
  /** 메서드 구현 필요 **/
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
