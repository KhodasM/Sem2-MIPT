package com.example.newsrecommendation.models.topic.repository;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.user.UserId;

import java.util.List;

public interface TopicRepository {
  Topic findById(TopicId topicId);

  Topic create(Topic topic);

  List<Topic> getAll();

  void delete(TopicId topicId, UserId userId);

}