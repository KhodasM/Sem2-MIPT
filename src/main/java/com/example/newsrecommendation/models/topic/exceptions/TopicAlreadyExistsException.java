package com.example.newsrecommendation.models.topic.exceptions;

public class TopicAlreadyExistsException extends RuntimeException {
  public TopicAlreadyExistsException(String message) {
    super(message);
  }
}