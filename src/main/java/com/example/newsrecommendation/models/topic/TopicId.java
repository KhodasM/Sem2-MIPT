package com.example.newsrecommendation.models.topic;

import java.util.Objects;

public class TopicId {
  private final Long value;

  public TopicId(Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof TopicId topicId) {
      return topicId.value.equals(value);
    }
    return false;
  }

  @Override
  public int hashCode(){
    return Objects.hash(value);
  }
}