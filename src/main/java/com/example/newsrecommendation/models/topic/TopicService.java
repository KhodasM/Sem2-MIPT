package com.example.newsrecommendation.models.topic;

import com.example.newsrecommendation.models.topic.repository.InMemoryTopicRepository;
import com.example.newsrecommendation.models.topic.repository.TopicRepository;
import com.example.newsrecommendation.models.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
  private static final Logger LOG = LoggerFactory.getLogger(TopicService.class);
  TopicRepository topicRepository;

  public TopicService(InMemoryTopicRepository topicRepository){
    this.topicRepository = topicRepository;
  }

  public Topic findById(TopicId topicId){
    LOG.debug("Method findById called");
    return topicRepository.findById(topicId);
  }

  public Topic create(Topic topic){
    LOG.debug("Method create called");
    return topicRepository.create(topic);
  }

  public List<Topic> getAll(){
    LOG.debug("Method getAll called");
    return topicRepository.getAll();
  }

  public void delete(TopicId topicId, UserId userId){
    LOG.debug("Method delete called");
    topicRepository.delete(topicId, userId);
  }
}