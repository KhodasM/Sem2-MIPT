package com.example.newsrecommendation.models.article;


import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.website.WebsiteId;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public record Article(ArticleId id, String title, String url, Timestamp createdAt, TopicId topicId, WebsiteId websiteId) {
  public static final Article ARTICLE_1 = new Article(new ArticleId(1L), "1","1", new Timestamp(new Date().getTime()), new TopicId(1L), new WebsiteId(1L));
  public static final Article ARTICLE_2 = new Article(new ArticleId(2L), "2","2", new Timestamp(new Date().getTime()), new TopicId(2L), new WebsiteId(2L));
  public Article initializeWithId(ArticleId newId) {
    return new Article(newId, title, url, createdAt, topicId, websiteId);
  }

  public Article withTitle(String newTitle) {
    return new Article(id, newTitle, url, createdAt, topicId, websiteId);
  }

  public Article withUrl(String newUrl) {
    return new Article(id, title, newUrl, createdAt, topicId, websiteId);
  }

  public Article withCreatedAt(Timestamp newCreatedAt) {
    return new Article(id, title, url, newCreatedAt, topicId, websiteId);
  }

  public Article withTopicId(TopicId newTopicId) {
    return new Article(id, title, url, createdAt, newTopicId, websiteId);
  }

  public Article withWebsiteId(WebsiteId newWebsiteId) {
    return new Article(id, title, url, createdAt, topicId, newWebsiteId);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Article article = (Article) o;
    return id != null && id.equals(article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}