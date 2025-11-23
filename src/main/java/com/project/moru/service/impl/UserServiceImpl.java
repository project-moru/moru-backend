package com.project.moru.service.impl;

import com.project.moru.common.pipeline.UserPipeline;
import com.project.moru.common.pipeline.context.UserContext;
import com.project.moru.common.pipeline.step.impl.*;
import com.project.moru.common.strategy.UserCreateMappingStrategy;
import com.project.moru.common.strategy.UserUpdateMappingStrategy;
import com.project.moru.common.validator.Validator;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import com.project.moru.domain.dto.user.UserResponseDto;
import com.project.moru.domain.dto.user.UserUpdateRequestDto;
import com.project.moru.mapper.struct.UserConverter;
import com.project.moru.service.UserDataService;
import com.project.moru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  
  private final UserConverter userConverter;
  private final PasswordEncoder passwordEncoder;
  private final Validator<UserCreateRequestDto> userCreateValidator;
  
  private final UserDataService userDataService;
  
  @Override
  public List<UserResponseDto> findAll() {
    return userConverter.toResList(userDataService.findAllUsers());
  }
  
  @Override
  public UserResponseDto findByUsername(String username) {
    UserPipeline<Void> pipeline = new UserPipeline<>(null);
    
    // 유저 정보 조회
    UserContext<Void> context = pipeline
        .addStep(new GetUserProfileFromUsernameStep<>(userDataService, username))
        .execute();
    
    return userConverter.fromEntityToRes( // Step 구현해야함.
        context.getUser()
    );
  }
  
  @Override
  public void create(UserCreateRequestDto dto) {
    UserPipeline<UserCreateRequestDto> pipeline = new UserPipeline<>(dto);
    
    // 로직 : 유저 검증 - Entity 변환 - 암호화 - 저장
    pipeline
        .addStep(new ValidateStep<>(userCreateValidator))
        .addStep(new MappingStep<>(new UserCreateMappingStrategy(userConverter)))
        .addStep(new EncryptPasswordStep<>(passwordEncoder))
        .addStep(new SaveStep<>(userDataService))
        .execute();
  }
  
  // 로직 : 유저 정보 조회 - Entity 변환 후 정보 저장 - 암호화(비밀번호 변경 요청시에만) - 저장
  @Override
  public UserResponseDto update(Long id, UserUpdateRequestDto dto) {
    UserPipeline<UserUpdateRequestDto> pipeline = new UserPipeline<>(dto);
    
    UserContext<UserUpdateRequestDto> context = pipeline
        .addStep(new GetUserProfileStep<>(userDataService, id))
        .addStep(new MappingStep<>(new UserUpdateMappingStrategy()))
        .addStep(new EncryptPasswordStep<>(passwordEncoder))
        .addStep(new SaveStep<>(userDataService))
        .execute();
    
    return userConverter.fromEntityToRes(context.getUser());
  }
  
  // 로직 : 유저 정보 조회 - 유저 활성화/비활성화 - 저장
  @Override
  public void toggleUserUseYn(Long id) {
    UserPipeline<Void> pipeline = new UserPipeline<>(null);
    
    pipeline
        .addStep(new GetUserProfileStep<>(userDataService, id))
        .addStep(new ConvertUserActivateStep<>())
        .addStep(new SaveStep<>(userDataService))
        .execute();
  }
  
  @Override
  public void delete(Long userId) {
    userDataService.deleteUserById(userId); // Step 구현해야함.
  }
}