package com.example.newsrecommendation.controllers;

import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.user.UserInfo;
import com.example.newsrecommendation.models.user.UserService;
import com.example.newsrecommendation.models.user.exceptions.EmailConflictException;
import com.example.newsrecommendation.models.user.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody UserInfo userInfo) throws EmailConflictException {
    User user = userService.register(new User(new UserId(null), userInfo.email(), userInfo.password(), userInfo.username()));
    LOG.debug("User is registered");
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserInfo> get(@PathVariable Long id){
    User user = userService.findById(new UserId(id));
    LOG.debug("User found by id = {}", id);
    return ResponseEntity.ok(new UserInfo(user.email(),user.username(), user.password()));
  }

  @PutMapping
  public ResponseEntity<String> update(@RequestBody User user) throws UserNotFoundException {
    userService.update(user.id(), user.email(), user.username());
    LOG.debug("Successfully updated user with id = {}", user.id().getValue());
    return ResponseEntity.ok("User is updated");
  }
}