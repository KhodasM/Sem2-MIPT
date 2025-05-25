package com.example.newsrecommendation.models.website.exception;

public class WebsiteAlreadyExistsException extends RuntimeException {
  public WebsiteAlreadyExistsException(String message) {
    super(message);
  }
}