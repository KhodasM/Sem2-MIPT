package com.example.newsrecommendation.models.user.exception;

public class EmailConflictException extends RuntimeException {
  public EmailConflictException(String message) {
    super(message);
  }
}