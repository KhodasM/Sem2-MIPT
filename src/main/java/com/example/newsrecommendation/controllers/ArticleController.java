package com.example.newsrecommendation.controllers;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.models.article.ArticleInfo;
import com.example.newsrecommendation.models.article.ArticleService;
import com.example.newsrecommendation.models.topic.TopicService;
import com.example.newsrecommendation.models.user.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
  private final ArticleService articleService;
  private final TopicService topicService;

  public ArticleController(ArticleService articleService, TopicService topicService) {
    this.articleService = articleService;
    this.topicService = topicService;
  }

  @GetMapping("user/{userId}")
  public ResponseEntity<List<ArticleInfo>> getUserArticles(@PathVariable Long userId){
    List<Article> articles = articleService.getUserArticles(new UserId(userId));
    List<ArticleInfo> articleInfos = new ArrayList<>();
    for(Article article : articles){
      String topicName = topicService.findById(article.topicId()).description();
      articleInfos.add(new ArticleInfo(article.title(),article.url(),article.createdAt(), topicName));
    }
    return ResponseEntity.ok(articleInfos);
  }
}