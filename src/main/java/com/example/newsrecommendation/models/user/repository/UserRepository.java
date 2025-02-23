package com.example.newsrecommendation.models.user.repository;

import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserId;

public interface UserRepository {
  User findById(UserId userId);

  User create(User user);

  void update(User user);

  void delete(UserId userId);
}
