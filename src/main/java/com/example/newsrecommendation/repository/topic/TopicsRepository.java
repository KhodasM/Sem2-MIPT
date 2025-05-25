package com.example.newsrecommendation.repository.topic;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.user.UserId;

import java.util.List;

public interface TopicsRepository {
  Topic findById(TopicId topicId);

  Topic create(Topic topic);

  List<Topic> getAll();

  List<Topic> findSubscribedTopicsByUserId(UserId creatorId);

  List<Topic> findUnSubscribedTopicsByUserId(UserId creatorId);

  void delete(TopicId topicId, UserId userId);

  void updateSubscribedTopics(List<TopicId> topics, UserId userId);

}