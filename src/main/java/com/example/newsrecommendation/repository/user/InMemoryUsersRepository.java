package com.example.newsrecommendation.repository.user;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.user.exception.EmailConflictException;
import com.example.newsrecommendation.models.user.exception.UserAuthenticationException;
import com.example.newsrecommendation.models.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class InMemoryUsersRepository implements UsersRepository {
  List<User> userList = new ArrayList<>(List.of(User.USER_1, User.USER_2, User.USER_3));
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryUsersRepository.class);

  @Override
  public User findById(UserId userId) {
    LOG.debug("Method findById called");
    for (User user : userList) {
      if (user.id().equals(userId)) {
        return user;
      }
    }
    throw new UserNotFoundException("User is not found with id = " + userId.getValue());
  }

  @Override
  public User create(User user) {
    LOG.debug("Method create called");
    for (User user1 : userList) {
      if (user1.email().equals(user.email())) {
        throw new EmailConflictException("Email is already used");
      }
    }
    return userList.get(1);
  }

  @Override
  public void update(User user) {
    LOG.debug("Method update called");
    for (User user1 : userList) {
      if (user1.email().equals(user.email())) {
        throw new EmailConflictException("Email is already used");
      }
    }
    for (User user1 : userList) {
      if (user1.id().equals(user.id())) {
        return;
      }
    }
  }

  @Override
  public void delete(UserId userId) {
  }

  @Override
  public Optional<UserId> authenticate(AuthenticationCredentials credentials) {
    LOG.debug("Method authenticate called");
    for (User user : userList) {
      if (user.email().equals(credentials.email()) && user.password().equals(credentials.password())) {
        return Optional.of(user.id());
      }
    }
    throw new UserAuthenticationException("User not authenticated by email = " + credentials.email() + " and password = " + credentials.password());
  }

  @Override
  public Optional<UserId> authenticateOptional(AuthenticationCredentials credentials) {
    LOG.debug("Method authenticateOptional called");
    for (User user : userList) {
      if (user.email().equals(credentials.email()) && user.password().equals(credentials.password())) {
        return Optional.of(user.id());
      }
    }
    return Optional.empty();
  }
}