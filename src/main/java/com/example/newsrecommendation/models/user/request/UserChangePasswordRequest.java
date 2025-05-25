package com.example.newsrecommendation.models.user.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;

public record UserChangePasswordRequest(String email, String password, String newPassword) {
  public AuthenticationCredentials getCredentials(){
    return new AuthenticationCredentials(email, password);
  }
}