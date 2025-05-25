package com.example.newsrecommendation.service;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.repository.user.InMemoryUsersRepository;
import com.example.newsrecommendation.repository.user.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
  private final UsersRepository userRepository;
  private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

  public UsersService(InMemoryUsersRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findById(UserId userId){
    LOG.debug("Method findById called");
    return userRepository.findById(userId);
  }

  public User register(User user){
    LOG.debug("Method register called");
    return userRepository.create(user);
  }

  public void update(UserId userId, String email, String username){
    LOG.debug("Method update called");
    User user = userRepository.findById(userId);
    userRepository.update(user.withEmail(email).withUsername(username));
  }
  public void delete(final UserId userId){
    LOG.debug("Method delete called");
    userRepository.delete(userId);
  }
  public Optional<UserId> authenticate(AuthenticationCredentials credentials){
    LOG.debug("Method authenticate called");
    return userRepository.authenticate(credentials);
  }
  public Optional<UserId> authenticateOptional(AuthenticationCredentials credentials){
    LOG.debug("Method authenticateOptional called");
    return userRepository.authenticateOptional(credentials);
  }

  public void changePassword(UserId userId, String newPassword){
    LOG.debug("Method changePassword called");
    User user = userRepository.findById(userId);
    userRepository.update(user.withPassword(newPassword));
  }
}