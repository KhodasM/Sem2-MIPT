package com.example.newsrecommendation.e2e;

import com.example.newsrecommendation.models.topic.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
    SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void should200OnGetUserArticles(){
    Topic testTopic = Topic.TOPIC_1;
    ResponseEntity<Topic> response = restTemplate.exchange("http://localhost:"+port+"/api/topics/"+testTopic.id().getValue(),HttpMethod.GET,HttpEntity.EMPTY, Topic.class);
    assertEquals(200, response.getStatusCode().value());
    assertEquals(testTopic, response.getBody());
  }
}