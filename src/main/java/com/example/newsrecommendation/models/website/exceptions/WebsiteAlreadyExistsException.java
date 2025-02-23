package com.example.newsrecommendation.models.website.exceptions;

public class WebsiteAlreadyExistsException extends RuntimeException {
  public WebsiteAlreadyExistsException(String message) {
    super(message);
  }
}