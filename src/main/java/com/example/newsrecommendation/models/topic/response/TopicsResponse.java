package com.example.newsrecommendation.models.topic.response;

import com.example.newsrecommendation.models.topic.Topic;

import java.util.List;

public record TopicsResponse(List<Topic> subscribed, List<Topic> other) {
}