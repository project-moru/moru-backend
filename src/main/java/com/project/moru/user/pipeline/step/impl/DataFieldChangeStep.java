package com.project.moru.user.pipeline.step.impl;

import com.project.moru.common.exception.ErrorCode;
import com.project.moru.common.exception.GeneralException;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.data_field.service_data.DataFieldDataService;
import com.project.moru.user.domain.dto.ChangeDataField;
import com.project.moru.user.domain.entity.User;
import com.project.moru.user.pipeline.Context;
import com.project.moru.user.pipeline.step.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataFieldChangeStep<T extends ChangeDataField> implements Step<T> {
  
  private final DataFieldDataService dataFieldDataService;
  
  @Override
  public void execute(Context<T> context) {
    Long dataFieldId = context.getDto().getDefaultDataFieldId();
    User user = context.getUser();
    
    if (dataFieldId == null) {
      user.clearDefaultDataField();
      return;
    }
    
    DataField dataField = dataFieldDataService.findById(dataFieldId);
    
    if (!dataField.getUser().getId().equals(user.getId())) {
      throw new GeneralException(ErrorCode.ACCESS_DENIED);
    }
    
    user.changeDefaultDataFieldId(dataFieldId);
  }
}
