package com.example.newsrecommendation.service;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.repository.topic.InMemoryTopicsRepository;
import com.example.newsrecommendation.repository.topic.TopicsRepository;
import com.example.newsrecommendation.models.topic.response.TopicsResponse;
import com.example.newsrecommendation.models.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsService {
  private static final Logger LOG = LoggerFactory.getLogger(TopicsService.class);
  TopicsRepository topicsRepository;

  public TopicsService(InMemoryTopicsRepository topicRepository){
    this.topicsRepository = topicRepository;
  }

  public Topic findById(TopicId topicId){
    LOG.debug("Method findById called");
    return topicsRepository.findById(topicId);
  }

  public Topic create(Topic topic){
    LOG.debug("Method create called");
    return topicsRepository.create(topic);
  }

  public List<Topic> getAll(){
    LOG.debug("Method getAll called");
    return topicsRepository.getAll();
  }

  public void delete(TopicId topicId, UserId userId){
    LOG.debug("Method delete called");
    topicsRepository.delete(topicId, userId);
  }

  public List<Topic> findSubscribedTopicsByUserId(UserId userId){
    LOG.debug("findSubscribedTopicsByUserId called");
    return topicsRepository.findSubscribedTopicsByUserId(userId);
  }

  public List<Topic> findUnSubscribedTopicsByUserId(UserId userId){
    LOG.debug("findUnSubscribedTopicsByUserId called");
    return topicsRepository.findUnSubscribedTopicsByUserId(userId);
  }

  public TopicsResponse findSubAndUnSubTopics(UserId userId){
    LOG.debug("Method findSubAndUnSubTopics called");
    return new TopicsResponse(topicsRepository.findSubscribedTopicsByUserId(userId), topicsRepository.findUnSubscribedTopicsByUserId(userId));
  }

  public void updateSubTopics(List<TopicId> topics, UserId userId){
    LOG.debug("updateSubTopics called");
    topicsRepository.updateSubscribedTopics(topics, userId);
  }
}