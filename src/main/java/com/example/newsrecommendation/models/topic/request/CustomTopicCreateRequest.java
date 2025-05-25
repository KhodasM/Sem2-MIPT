package com.example.newsrecommendation.models.topic.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;

public record CustomTopicCreateRequest(String email, String password, String description) {
  public AuthenticationCredentials getCredentials(){
    return new AuthenticationCredentials(email, password);
  }
}