package com.example.newsrecommendation.controllers;

import com.example.newsrecommendation.models.topic.Topic;
import com.example.newsrecommendation.models.topic.TopicId;
import com.example.newsrecommendation.models.topic.TopicInfo;
import com.example.newsrecommendation.models.topic.TopicService;
import com.example.newsrecommendation.models.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topics")
public class TopicController {
  private static final Logger LOG = LoggerFactory.getLogger(TopicController.class);
  private final TopicService topicService;

  public TopicController(TopicService topicService) {
    this.topicService = topicService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Topic> get(@PathVariable Long id){
    Topic topic = topicService.findById(new TopicId(id));
    LOG.debug("Successfully found topic with id = "+id);
    return ResponseEntity.ok(topic);
  }

  @PostMapping
  public ResponseEntity<Topic> create(@RequestBody TopicInfo topicInfo){
    Topic topic = topicService.create(new Topic(new TopicId(null), topicInfo.description(), new UserId(topicInfo.userId())));
    LOG.debug("Successfully created topic by user with id = "+topicInfo.userId());
    return ResponseEntity.ok(topic);
  }

  @DeleteMapping("/{userId}/{topicId}")
  public ResponseEntity<String> deleteUserTopic(@PathVariable Long userId, @PathVariable Long topicId){
    topicService.delete(new TopicId(topicId), new UserId(userId));
    LOG.debug("Successfully deleted topic with id = {} by user with id = {}", topicId, userId);
    return ResponseEntity.ok("Topic deleted");
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Topic>> getUserTopics(@PathVariable Long userId){
    List<Topic> topics = topicService.getAll();
    LOG.debug("User topics found");
    return ResponseEntity.ok(topics);
  }
}