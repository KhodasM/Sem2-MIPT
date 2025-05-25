package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.controllers.user.UserController;
import com.example.newsrecommendation.models.user.*;
import com.example.newsrecommendation.models.user.exception.EmailConflictException;
import com.example.newsrecommendation.models.user.exception.UserAuthenticationException;
import com.example.newsrecommendation.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UsersControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UsersService usersService;

  private static final String USER_REGISTER = "{\"email\":\"2\", \"password\":\"2\", \"username\":\"2\"}";
  private static final String USER_AUTHENTICATE = "{\"email\":\"2\", \"password\":\"2\"}";
  private static final String USER_UPDATE = "{\"email\":\"2\", \"password\":\"2\", \"newEmail\":\"4\", \"newUsername\":\"4\"}";
  private static final String USER_CHANGE_PASSWORD = "{\"email\":\"2\", \"password\":\"2\", \"newPassword\":\"2\"}";
  private final User testUser = User.USER_2;
  @Test
  public void should201OnRegister() throws Exception {
    when(usersService.register(any(User.class))).thenReturn(testUser);
    mockMvc.perform(post("/api/user/register").contentType("application/json").content(USER_REGISTER))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.username").value(testUser.username()))
        .andExpect(jsonPath("$.email").value(testUser.email()))
        .andExpect(jsonPath("$.password").value(testUser.password()))
        .andExpect(jsonPath("$.id.value").value(testUser.id().getValue()));
  }

  @Test
  public void should400OnRegister() throws Exception{
    when(usersService.register(any(User.class))).thenThrow(new EmailConflictException("email is already used"));
    mockMvc.perform(post("/api/user/register").contentType("application/json").content(USER_REGISTER))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void should200OnGetUser() throws Exception {
    UserInfo testInfo = new UserInfo(testUser.email(), testUser.username());
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenReturn(Optional.of(new UserId(2L)));
    when(usersService.findById(any(UserId.class))).thenReturn(testUser);
    mockMvc.perform(get("/api/user").contentType("application/json").content(USER_AUTHENTICATE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(testInfo.email()))
        .andExpect(jsonPath("$.username").value(testInfo.username()));
  }

  @Test
  public void should400OnGetUser() throws Exception{
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenThrow(new UserAuthenticationException(""));
    mockMvc.perform(get("/api/user").contentType("application/json").content(USER_AUTHENTICATE))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void should200OnUpdateUser() throws Exception {
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenReturn(Optional.of(new UserId(2L)));
    doNothing().when(usersService).update(any(UserId.class), any(String.class), any(String.class));
    mockMvc.perform(patch("/api/user").contentType("application/json").content(USER_UPDATE))
        .andExpect(status().isOk());
  }

  @Test
  public void should400OnUpdateUser() throws Exception {
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenThrow(new UserAuthenticationException(""));
    mockMvc.perform(patch("/api/user").contentType("application/json").content(USER_UPDATE))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void should200OnChangePassword() throws Exception {
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenReturn(Optional.of(new UserId(2L)));
    doNothing().when(usersService).changePassword(any(UserId.class), any(String.class));
    mockMvc.perform(put("/api/user/password").contentType("application/json").content(USER_CHANGE_PASSWORD))
        .andExpect(status().isOk());
  }

  @Test
  public void should400OnChangePassword() throws Exception {
    when(usersService.authenticate(any(AuthenticationCredentials.class))).thenThrow(new UserAuthenticationException(""));
    mockMvc.perform(put("/api/user/password").contentType("application/json").content(USER_CHANGE_PASSWORD))
        .andExpect(status().isBadRequest());
  }
}