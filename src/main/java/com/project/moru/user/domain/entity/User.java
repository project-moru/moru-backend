package com.project.moru.user.domain.entity;

import com.project.moru.common.constant.Job;
import com.project.moru.common.constant.Use;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.user.domain.dto.UserUpdateRequestDto;
import com.project.moru.common.domain.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {
  
  @Column(nullable = false, length = 50)
  private String username;
  
  @Column(nullable = false)
  private String password;
  
  @Column(length = 100)
  private String nickname;
  
  @Enumerated(EnumType.STRING)
  @Column(length = 50)
  private Job job;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "use_yn", nullable = false)
  private Use useYn = Use.Y;
  
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DataField> dataFields = new ArrayList<>();
  
  public void update(UserUpdateRequestDto dto) {
    if (dto.getUsername() != null) this.username = dto.getUsername();
    if (dto.getNickname() != null) this.nickname = dto.getNickname();
    if (dto.getJob() != null) this.job = dto.getJob();
  }
  
  public void updatePassword(String encodedPassword) {
    this.password = encodedPassword;
  }

  public void convertUseYn() {
    if (this.useYn == Use.Y) {
      this.useYn = Use.N;
    } else {
      this.useYn = Use.Y;
    }
  }
}