package com.example.newsrecommendation.models.user.repository;

import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.user.exceptions.EmailConflictException;
import com.example.newsrecommendation.models.user.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class InMemoryUserRepository implements UserRepository{
  List<User> userList = new ArrayList<>(List.of(User.USER_1, User.USER_2, User.USER_3));
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepository.class);

  @Override
  public User findById(UserId userId) {
    LOG.debug("Method findById called");
    for(User user : userList){
      if (user.id().equals(userId)){
        return user;
      }
    }
    throw new UserNotFoundException("User is not found with id = " + userId.getValue());
  }

  @Override
  public User create(User user){
    LOG.debug("Method create called");
    for (User user1 : userList){
      if (user1.email().equals(user.email())){
        throw new EmailConflictException("email is already used");
      }
    }
    return userList.get(1);
  }

  @Override
  public void update(User user){
    LOG.debug("Method update called");
    for (User user1 : userList){
      if (user1.id().equals(user.id())){
        return;
      }
    }
  }

  @Override
  public void delete(UserId userId) {
  }
}