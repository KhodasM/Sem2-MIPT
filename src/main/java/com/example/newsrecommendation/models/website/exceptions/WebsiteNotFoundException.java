package com.example.newsrecommendation.models.website.exceptions;


public class WebsiteNotFoundException extends RuntimeException {
  public WebsiteNotFoundException(String message) {
    super(message);
  }
}