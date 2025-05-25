package com.example.newsrecommendation.models.article;

import java.util.Objects;

public class ArticleId {
  private final Long value;

  public ArticleId(Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof ArticleId articleId) {
      return articleId.value.equals(value);
    }
    return false;
  }

  @Override
  public int hashCode(){
    return Objects.hash(value);
  }
}