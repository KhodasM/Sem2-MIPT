package com.example.newsrecommendation.controllers.article;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.models.article.ArticleInfo;
import com.example.newsrecommendation.service.ArticlesService;
import com.example.newsrecommendation.service.TopicsService;
import com.example.newsrecommendation.models.user.AuthenticationCredentials;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.service.UsersService;
import com.example.newsrecommendation.models.user.exception.UserAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/articles")
public class ArticleController implements ArticleOperations{
  private final ArticlesService articlesService;
  private final TopicsService topicsService;
  private final UsersService usersService;
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  public ArticleController(ArticlesService articlesService, TopicsService topicsService, UsersService usersService){
    this.articlesService = articlesService;
    this.topicsService = topicsService;
    this.usersService = usersService;
  }

  public ResponseEntity<List<ArticleInfo>> getUserArticles(@RequestBody AuthenticationCredentials credentials) throws UserAuthenticationException {
    Optional<UserId> userId = usersService.authenticate(credentials);
    List<Article> articles = articlesService.getUserArticles(userId.get());
    List<ArticleInfo> articleInfos = new ArrayList<>();
    for(Article article : articles){
      String topicName = topicsService.findById(article.topicId()).description();
      articleInfos.add(new ArticleInfo(article.title(),article.url(),article.createdAt(), topicName));
    }
    LOG.debug("Successfully got articles for user with id = {}", userId.get().getValue());
    return ResponseEntity.ok(articleInfos);
  }
}