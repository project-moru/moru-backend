package com.project.moru.common.pipeline;

import com.project.moru.common.pipeline.context.UserContext;

public interface Pipeline<T> {
  UserContext<T> execute();
}