package com.example.newsrecommendation.models.website.request;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;

import java.util.List;

public record SubWebsitesUpdateRequest(String email, String password, List<Long> websiteIds) {
  public AuthenticationCredentials getCredentials(){
    return new AuthenticationCredentials(email, password);
  }
}