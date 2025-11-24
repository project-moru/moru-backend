package com.project.moru.mapper.struct;

import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.entity.user.User;
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