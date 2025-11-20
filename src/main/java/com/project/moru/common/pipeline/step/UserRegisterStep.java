package com.project.moru.common.pipeline.step;

import com.project.moru.common.pipeline.context.UserRegisterContext;

public interface UserRegisterStep {
  void execute(UserRegisterContext context);
}
