package com.example.newsrecommendation.models.topic.exceptions;

public class TopicNotFoundException extends RuntimeException {
  public TopicNotFoundException(String message) {
    super(message);
  }
}