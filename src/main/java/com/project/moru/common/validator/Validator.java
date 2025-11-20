package com.project.moru.common.validator;

public interface Validator<T> {
  void validate(T target);
}
