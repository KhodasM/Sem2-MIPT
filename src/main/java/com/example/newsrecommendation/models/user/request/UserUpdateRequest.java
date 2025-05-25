package com.example.newsrecommendation.models.user.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.user.UserInfo;

public record UserUpdateRequest(String email, String password, String newEmail, String newUsername) {
  public AuthenticationCredentials getCredentials(){
    return new AuthenticationCredentials(email, password);
  }
  public UserInfo getNewUserInfo(){
    return new UserInfo(newEmail, newUsername);
  }
}