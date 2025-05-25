package com.example.newsrecommendation.controllers.website;

import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.website.Website;
import com.example.newsrecommendation.models.website.request.CustomWebsiteCreateRequest;
import com.example.newsrecommendation.models.website.request.SubWebsitesUpdateRequest;
import com.example.newsrecommendation.models.website.response.WebsitesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
@Tag(name = "WebsiteAPI", description = "Управление сайтами")
public interface WebsiteOperations {
  @Operation(summary = "Найти сайт по ID")
  @ApiResponse(responseCode = "200", description = "Сайт найден")
  @GetMapping("/{id}")
  ResponseEntity<Website> get(@Parameter(description = "ID сайта для получения") @PathVariable Long id);

  @Operation(summary = "Создать сайт")
  @ApiResponse(responseCode = "200", description = "Сайт создан")
  @PostMapping("/custom")
  ResponseEntity<Website> create(@Parameter(description = "Данные для создания Сайта пользователем") @RequestBody CustomWebsiteCreateRequest customWebsiteCreateRequest);

  @Operation(summary = "Удалить созданный пользователем сайт")
  @ApiResponse(responseCode = "200", description = "Сайт удален")
  @DeleteMapping("/custom/{websiteId}")
  ResponseEntity<String> delete(@Parameter(description = "Реквизиты для входа пользователя") @RequestBody AuthenticationCredentials credentials, @Parameter(description = "ID созданного сайта для удаления") @PathVariable Long websiteId);

  @Operation(summary = "Получить все выбранные пользователем сайты")
  @ApiResponse(responseCode = "200", description = "Сайты получены")
  @GetMapping("/user")
  ResponseEntity<WebsitesResponse> getUsersWebsites(@Parameter(description = "Реквизиты для входа пользователя") @RequestBody AuthenticationCredentials credentials);

  @Operation(summary = "Установить выбранные сайты для пользователя")
  @ApiResponse(responseCode = "200", description = "Выбранные сайты обновлены")
  @PatchMapping
  ResponseEntity<String> patch(@Parameter(description = "Данные для обновления выбранных сайтов") @RequestBody SubWebsitesUpdateRequest subWebsitesUpdateRequest);
}