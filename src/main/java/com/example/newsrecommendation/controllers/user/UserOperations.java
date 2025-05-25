package com.example.newsrecommendation.controllers.user;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.user.User;
import com.example.newsrecommendation.models.user.UserInfo;
import com.example.newsrecommendation.models.user.request.UserChangePasswordRequest;
import com.example.newsrecommendation.models.user.request.UserRegisterRequest;
import com.example.newsrecommendation.models.user.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
@Tag(name = "UserAPI", description = "Управление пользователями")
public interface UserOperations {

  @Operation(summary = "Регистрация пользователя")
  @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован")
  @PostMapping("/register")
  ResponseEntity<User> register(@Parameter(description = "Данные для регистрации пользователя") @RequestBody UserRegisterRequest userRegisterRequest);

  @Operation(summary = "Обновление имени и почты пользователя")
  @ApiResponse(responseCode = "200", description = "Данные пользователя обновлены")
  @PatchMapping
  ResponseEntity<String> update(@Parameter(description = "Данные для обновления пользователя") @RequestBody UserUpdateRequest userUpdateRequest);

  @Operation(summary = "Авторизация пользователя и получения его данных")
  @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизовался")
  @GetMapping
  ResponseEntity<UserInfo> get(@Parameter(description = "Реквизиты для входа пользователя") @RequestBody AuthenticationCredentials credentials);

  @Operation(summary = "Смена пароля пользователя")
  @ApiResponse(responseCode = "200", description = "Пароль изменен")
  @PutMapping("/password")
  ResponseEntity<String> changePassword(@Parameter(description = "Данные для смены пароля пользователя") @RequestBody UserChangePasswordRequest userChangePasswordRequest);
}
