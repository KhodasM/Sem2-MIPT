package com.example.newsrecommendation.models.topic.exception;

public class TopicNotFoundException extends RuntimeException {
  public TopicNotFoundException(String message) {
    super(message);
  }
}