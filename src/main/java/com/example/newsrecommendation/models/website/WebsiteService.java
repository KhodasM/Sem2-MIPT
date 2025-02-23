package com.example.newsrecommendation.models.website;

import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.website.repository.InMemoryWebsiteRepository;
import com.example.newsrecommendation.models.website.repository.WebsiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {
  private static final Logger LOG = LoggerFactory.getLogger(WebsiteService.class);
  private final WebsiteRepository websiteRepository;

  public WebsiteService(InMemoryWebsiteRepository websiteRepository) {
    this.websiteRepository = websiteRepository;
  }

  public Website findById(WebsiteId websiteId) {
    LOG.debug("Method findById called");
    return websiteRepository.findById(websiteId);
  }

  public List<Website> getAll() {
    LOG.debug("Method getAll called");
    return websiteRepository.getAll();
  }

  public List<Website> getSubscribedWebsitesByUserId(UserId userId) {
    LOG.debug("Method getSubscribedWebsitesByUserId called");
    return websiteRepository.findSubscribedWebsitesByUserId(userId);
  }

  public List<Website> getUnSubscribedWebsitesByUserId(UserId userId) {
    LOG.debug("Method getUnSubscribedWebsitesByUserId called");
    return websiteRepository.findUnSubscribedWebsitesByUserId(userId);
  }

  public Website create(Website website) {
    LOG.debug("Method create called");
    return websiteRepository.create(website);
  }

  public void update(WebsiteId websiteId, String url, String description) {
    LOG.debug("Method update called");
    Website website = websiteRepository.findById(websiteId);
    websiteRepository.update(website.withUrl(url).withDescription(description));
  }

  public void delete(WebsiteId websiteId, UserId userId) {
    LOG.debug("Method delete called");
    websiteRepository.delete(websiteId, userId);
  }
}