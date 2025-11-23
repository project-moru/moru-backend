package com.project.moru.common.pipeline.step;

import com.project.moru.common.pipeline.context.UserContext;

public interface UserStep<T> {
  void execute(UserContext<T> context);
}