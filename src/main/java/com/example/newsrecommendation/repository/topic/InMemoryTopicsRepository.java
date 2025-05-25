package com.example.newsrecommendation.repository.topic;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.topic.exception.TopicAlreadyExistsException;
import com.example.newsrecommendation.models.topic.exception.TopicNotFoundException;
import com.example.newsrecommendation.models.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTopicsRepository implements TopicsRepository {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryTopicsRepository.class);
  List<Topic> topicList = new ArrayList<>(List.of(Topic.TOPIC_1, Topic.TOPIC_2, Topic.TOPIC_3));

  @Override
  public Topic findById(TopicId topicId) {
    LOG.debug("Method findById called");
    for (Topic topic : topicList) {
      if (topic.id().equals(topicId)) {
        return topicList.get(0);
      }
    }
    throw new TopicNotFoundException("Topic not found with id = " + topicId.getValue());
  }

  @Override
  public Topic create(Topic topic) {
    LOG.debug("Method create called");
    for (Topic topic1 : topicList) {
      if (topic.description().equals(topic1.description())) {
        throw new TopicAlreadyExistsException("Topic already exists with description = " + topic.description());
      }
    }
    return topicList.get(1);
  }

  @Override
  public List<Topic> getAll() {
    LOG.debug("Method getAll called");
    return topicList;
  }

  @Override
  public List<Topic> findSubscribedTopicsByUserId(UserId creatorId) {
    LOG.debug("findSubscribedTopicsByUserId called");
    return List.of(topicList.get(0), topicList.get(1));
  }

  @Override
  public List<Topic> findUnSubscribedTopicsByUserId(UserId creatorId) {
    LOG.debug("findUnSubscribedTopicsByUserId called");
    return List.of(topicList.get(2));
  }


  @Override
  public void delete(TopicId topicId, UserId userId) {
    LOG.debug("Method delete called");
    for (Topic topic : topicList) {
      if (topic.id().equals(topicId)) {
        return;
      }
    }
    throw new TopicNotFoundException("Topic not found with id = " + topicId.getValue());
  }

  @Override
  public void updateSubscribedTopics(List<TopicId> topics, UserId userId) {
    LOG.debug("updateSubscribedTopics called");
  }
}