package com.project.moru.domain.entity.user;

import com.project.moru.common.Job;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")  // DB 컬럼: user_id (snake_case)
  private Long userId;  // Java 필드: userId (camelCase)
  
  @Column(name = "username", nullable = false, length = 50)
  private String username;
  
  @Column(nullable = false)
  private String password;
  
  @Column(name = "nickname", length = 100)
  private String nickname;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "job", length = 50)
  private Job job;
  
  public void update(UserUpdateRequestDto dto) {
    if (dto.getUsername() != null) this.username = dto.getUsername();
    if (dto.getNickname() != null) this.nickname = dto.getNickname();
    if (dto.getJob() != null) this.job = dto.getJob();
  }
  
  public void updatePassword(String encodedPassword) {
    this.password = encodedPassword;
  }
}
