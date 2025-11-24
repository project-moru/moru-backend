package com.project.moru.user.mapper.struct;

import com.project.moru.user.domain.dto.UserCreateRequestDto;
import com.project.moru.user.domain.dto.UserResponseDto;
import com.project.moru.user.domain.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserConverter {
  @Mapping(target = "useYn", constant = "Y")
  User fromCreateReqToEntity(UserCreateRequestDto dto);
  
  UserResponseDto fromEntityToRes(User user);
  
  List<UserResponseDto> toResList(List<User> users);
}