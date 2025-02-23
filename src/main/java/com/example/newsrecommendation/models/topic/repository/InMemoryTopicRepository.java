package com.example.newsrecommendation.models.topic.repository;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.newsrecommendation.models.topic.exceptions.TopicNotFoundException;
import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.website.exceptions.WebsiteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTopicRepository implements TopicRepository{
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryTopicRepository.class);
  List<Topic> topicList = new ArrayList<>(List.of(Topic.TOPIC_1,Topic.TOPIC_2));

  @Override
  public Topic findById(TopicId topicId) {
    LOG.debug("Method findById called");
    for(Topic topic : topicList){
      if (topic.id().equals(topicId)){
        return topicList.get(0);
      }
    }
    throw new TopicNotFoundException("Topic not found with id = "+ topicId.getValue());
  }

  @Override
  public Topic create(Topic topic) {
    LOG.debug("Method create called");
    for(Topic topic1 : topicList){
      if (topic.description().equals(topic1.description())){
        throw new TopicAlreadyExistsException("Topic already exists with description = "+topic.description());
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
  public void delete(TopicId topicId, UserId userId) {
    LOG.debug("Method delete called");
    for(Topic topic : topicList){
      if (topic.id().equals(topicId)){
        return;
      }
    }
    throw new TopicNotFoundException("Topic not found with id = "+topicId.getValue());
  }
}