package com.example.newsrecommendation.models.article;

import com.example.newsrecommendation.models.article.repository.ArticleRepository;
import com.example.newsrecommendation.models.article.repository.InMemoryArticleRepository;
import com.example.newsrecommendation.models.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
  private final ArticleRepository articleRepository;

  public ArticleService(InMemoryArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public List<Article> getUserArticles(UserId userId){
    return articleRepository.getUserArticles(userId);
  }
}