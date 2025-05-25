package com.example.newsrecommendation.models.website.response;

import com.example.newsrecommendation.models.website.Website;

import java.util.List;

public record WebsitesResponse(List<Website> subscribed, List<Website> other) {
}