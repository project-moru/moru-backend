package com.project.moru.common.pipeline.step;

import com.project.moru.common.pipeline.context.UserUpdateContext;

public interface UserUpdateStep {
  void execute(UserUpdateContext context);
}
