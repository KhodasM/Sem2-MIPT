package com.example.newsrecommendation.service;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.repository.article.ArticlesRepository;
import com.example.newsrecommendation.repository.article.InMemoryArticlesRepository;
import com.example.newsrecommendation.models.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {
  private final ArticlesRepository articleRepository;

  public ArticlesService(InMemoryArticlesRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public List<Article> getUserArticles(UserId userId){
    return articleRepository.getUserArticles(userId);
  }
}