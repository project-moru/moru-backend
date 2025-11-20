package com.project.moru.common.pipeline;

import com.project.moru.common.pipeline.step.UserRegisterStep;
import com.project.moru.domain.dto.user.UserCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterPipelineTest {
  
  @Mock private UserRegisterStep validateStep;
  @Mock private UserRegisterStep encryptStep;
  @Mock private UserRegisterStep mappingStep;
  @Mock private UserRegisterStep saveStep;
  
  private UserRegisterPipeline pipeline;
  
  @BeforeEach
  void setUp() {
    UserCreateRequestDto dto =
        new UserCreateRequestDto("user01", "Pass!123", "nick", null);
    
    pipeline = new UserRegisterPipeline(dto)
        .addStep(validateStep)
        .addStep(encryptStep)
        .addStep(mappingStep)
        .addStep(saveStep);
  }
  
  // ---------------------------------------------------------------------------
  // 1. 모든 Step들이 순서대로 호출되는지 검증
  // ---------------------------------------------------------------------------
  @Test
  void pipeline_shouldExecuteStepsInOrder() {
    // when
    pipeline.execute();
    
    // then
    InOrder inOrder = inOrder(validateStep, encryptStep, mappingStep, saveStep);
    
    inOrder.verify(validateStep).execute(any());
    inOrder.verify(encryptStep).execute(any());
    inOrder.verify(mappingStep).execute(any());
    inOrder.verify(saveStep).execute(any());
  }
  
  // ---------------------------------------------------------------------------
  // 2. 모든 Step들이 각각 정확히 한 번씩 호출되는지 검증
  // ---------------------------------------------------------------------------
  @Test
  void pipeline_shouldExecuteEachStepOnce() {
    // when
    pipeline.execute();
    
    // then
    verify(validateStep, times(1)).execute(any());
    verify(encryptStep, times(1)).execute(any());
    verify(mappingStep, times(1)).execute(any());
    verify(saveStep, times(1)).execute(any());
  }
}