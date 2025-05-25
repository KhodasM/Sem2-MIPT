package com.example.newsrecommendation.controllers.user;

import com.example.newsrecommendation.models.user.*;
import com.example.newsrecommendation.models.user.exception.EmailConflictException;
import com.example.newsrecommendation.models.user.exception.UserAuthenticationException;
import com.example.newsrecommendation.models.user.request.UserChangePasswordRequest;
import com.example.newsrecommendation.models.user.request.UserRegisterRequest;
import com.example.newsrecommendation.models.user.request.UserUpdateRequest;
import com.example.newsrecommendation.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController implements UserOperations{
  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
  private final UsersService usersService;

  public UserController(UsersService usersService) {
    this.usersService = usersService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody UserRegisterRequest userRegisterRequest) throws EmailConflictException {
    User user = usersService.register(new User(new UserId(null), userRegisterRequest.email(), userRegisterRequest.password(), userRegisterRequest.username()));
    LOG.debug("User is registered");
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  public ResponseEntity<UserInfo> get(@RequestBody AuthenticationCredentials credentials) throws UserAuthenticationException {
    Optional<UserId> userId = usersService.authenticate(credentials);
    User user = usersService.findById(userId.get());
    LOG.debug("User authenticated by email = {} and password = {}", credentials.email(), credentials.password());
    return ResponseEntity.ok(new UserInfo(user.email(),user.username()));
  }

  public ResponseEntity<String> update(@RequestBody UserUpdateRequest userUpdateRequest) throws UserAuthenticationException, EmailConflictException {
    Optional<UserId> userId = usersService.authenticate(userUpdateRequest.getCredentials());
    UserInfo newUserInfo = userUpdateRequest.getNewUserInfo();
    usersService.update(userId.get(), newUserInfo.email(), newUserInfo.username());
    LOG.debug("Successfully updated user with id = {}", userId.get().getValue());
    return ResponseEntity.ok("User is updated");
  }

  public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordRequest userChangePasswordRequest) throws UserAuthenticationException{
    Optional<UserId> userId = usersService.authenticate(userChangePasswordRequest.getCredentials());
    String newPassword = userChangePasswordRequest.newPassword();
    usersService.changePassword(userId.get(), newPassword);
    LOG.debug("Successfully updated password by user with id = {}", userId.get().getValue());
    return ResponseEntity.ok("Password is updated");
  }
}