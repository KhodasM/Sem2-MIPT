package com.example.newsrecommendation;

import com.example.newsrecommendation.models.topic.exception.TopicAlreadyExistsException;
import com.example.newsrecommendation.models.topic.exception.TopicNotFoundException;
import com.example.newsrecommendation.models.user.exception.EmailConflictException;
import com.example.newsrecommendation.models.user.exception.UserAuthenticationException;
import com.example.newsrecommendation.models.user.exception.UserNotFoundException;
import com.example.newsrecommendation.models.website.exception.WebsiteAlreadyExistsException;
import com.example.newsrecommendation.models.website.exception.WebsiteNotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ApiResponse(responseCode = "400", description = "Такая почта уже существует")
  @ExceptionHandler(EmailConflictException.class)
  public ResponseEntity<String> handleEmailConflictException(EmailConflictException ex) {
    LOG.warn("Email conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Такой пользователь не найден")
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    LOG.warn("User not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Такой сайт уже создан")
  @ExceptionHandler(WebsiteAlreadyExistsException.class)
  public ResponseEntity<String> handleWebsiteAlreadyExistsException(WebsiteAlreadyExistsException ex) {
    LOG.warn("Website conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Такой сайт не найден")
  @ExceptionHandler(WebsiteNotFoundException.class)
  public ResponseEntity<String> handleWebsiteNotFoundException(WebsiteNotFoundException ex) {
    LOG.warn("Website not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Такая тема не найдена")
  @ExceptionHandler(TopicNotFoundException.class)
  public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException ex) {
    LOG.warn("Topic not found: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Такая тема уже создана")
  @ExceptionHandler(TopicAlreadyExistsException.class)
  public ResponseEntity<String> handleTopicAlreadyExistsException(TopicAlreadyExistsException ex) {
    LOG.warn("Topic conflict: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ApiResponse(responseCode = "400", description = "Неправильные реквизиты для входа")
  @ExceptionHandler(UserAuthenticationException.class)
  public ResponseEntity<String> handleUserAuthenticationException(UserAuthenticationException ex){
    LOG.warn("User not authenticated: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}