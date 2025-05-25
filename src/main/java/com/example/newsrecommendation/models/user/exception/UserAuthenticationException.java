package com.example.newsrecommendation.models.user.exception;

public class UserAuthenticationException extends RuntimeException {
  public UserAuthenticationException(String message) {
    super(message);
  }
}