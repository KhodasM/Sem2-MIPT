package com.example.newsrecommendation.models.website.exception;


public class WebsiteNotFoundException extends RuntimeException {
  public WebsiteNotFoundException(String message) {
    super(message);
  }
}