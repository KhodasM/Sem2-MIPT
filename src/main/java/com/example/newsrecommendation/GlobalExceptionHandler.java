package com.example.newsrecommendation;

import com.example.newsrecommendation.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.newsrecommendation.models.topic.exceptions.TopicNotFoundException;
import com.example.newsrecommendation.models.user.exceptions.EmailConflictException;
import com.example.newsrecommendation.models.user.exceptions.UserNotFoundException;
import com.example.newsrecommendation.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.newsrecommendation.models.website.exceptions.WebsiteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(EmailConflictException.class)
  public ResponseEntity<String> handleEmailConflictException(EmailConflictException ex) {
    LOG.warn("Email conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    LOG.warn("User not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(WebsiteAlreadyExistsException.class)
  public ResponseEntity<String> handleWebsiteAlreadyExistsException(WebsiteAlreadyExistsException ex) {
    LOG.warn("Website conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(WebsiteNotFoundException.class)
  public ResponseEntity<String> handleWebsiteNotFoundException(WebsiteNotFoundException ex) {
    LOG.warn("Website not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(TopicNotFoundException.class)
  public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException ex) {
    LOG.warn("Topic not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(TopicAlreadyExistsException.class)
  public ResponseEntity<String> handleTopicAlreadyExistsException(TopicAlreadyExistsException ex) {
    LOG.warn("Topic conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}