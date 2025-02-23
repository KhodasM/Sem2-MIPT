package com.example.newsrecommendation.models.article.repository;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.models.user.UserId;

import java.util.List;

public interface ArticleRepository {
  List<Article> getUserArticles(UserId userId);
}