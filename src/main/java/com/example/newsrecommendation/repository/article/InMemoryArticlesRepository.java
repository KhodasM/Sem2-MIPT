package com.example.newsrecommendation.repository.article;

import com.example.newsrecommendation.models.article.Article;
import com.example.newsrecommendation.models.user.UserId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryArticlesRepository implements ArticlesRepository {
  List<Article> articleList = new ArrayList<>(List.of(Article.ARTICLE_1, Article.ARTICLE_2));

  @Override
  public List<Article> getUserArticles(UserId userId) {
    return articleList;
  }
}