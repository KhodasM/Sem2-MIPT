package com.example.newsrecommendation.models.topic.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;

import java.util.List;

public record SubTopicsUpdateRequest(String email, String password, List<Long> topicIds) {
  public AuthenticationCredentials gerCredentials(){
    return new AuthenticationCredentials(email, password);
  }
}