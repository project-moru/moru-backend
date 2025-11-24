package com.project.moru.user.pipeline;

public interface Pipeline<T> {
  Context<T> execute();
}