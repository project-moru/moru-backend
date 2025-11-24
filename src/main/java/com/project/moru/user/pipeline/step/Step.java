package com.project.moru.user.pipeline.step;

import com.project.moru.user.pipeline.Context;

public interface Step<T> {
  void execute(Context<T> context);
}