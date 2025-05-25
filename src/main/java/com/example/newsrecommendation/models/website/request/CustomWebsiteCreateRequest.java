package com.example.newsrecommendation.models.website.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;

public record CustomWebsiteCreateRequest(String email, String password, String url, String description) {
  public AuthenticationCredentials getCredentials(){
    return new AuthenticationCredentials(email, password);
  }
}