package com.example.newsrecommendation.repository.article;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.models.user.UserId;

import java.util.List;

public interface ArticlesRepository {
  List<Article> getUserArticles(UserId userId);
}