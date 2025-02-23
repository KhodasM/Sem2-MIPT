package com.example.newsrecommendation.models.user.exceptions;

public class EmailConflictException extends RuntimeException {
  public EmailConflictException(String message) {
    super(message);
  }
}