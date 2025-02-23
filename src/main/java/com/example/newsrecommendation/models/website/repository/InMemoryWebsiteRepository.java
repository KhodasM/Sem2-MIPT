package com.example.newsrecommendation.models.website.repository;

import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.website.Website;
import com.example.newsrecommendation.models.website.WebsiteId;
import com.example.newsrecommendation.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.newsrecommendation.models.website.exceptions.WebsiteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class InMemoryWebsiteRepository implements WebsiteRepository {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryWebsiteRepository.class);
  private final List<Website> websiteList = new ArrayList<>(List.of(Website.WEBSITE_1, Website.WEBSITE_2, Website.WEBSITE_3));

  @Override
  public Website findById(WebsiteId websiteId) {
    LOG.debug("Method findById called");
    for (Website website : websiteList) {
      if (website.id().equals(websiteId)) {
        return website;
      }
    }
    throw new WebsiteNotFoundException("Website is not found with id = " + websiteId.getValue());
  }

  @Override
  public Website create(Website website) {
    LOG.debug("Method create called");
    for (Website website1 : websiteList) {
      if (website.url().equals(website1.url())) {
        throw new WebsiteAlreadyExistsException("Website already exists with url = " + website.url());
      }
    }
    return websiteList.get(0);
  }

  @Override
  public List<Website> getAll() {
    LOG.debug("Method getAll called");
    return websiteList;
  }


  @Override
  public List<Website> findSubscribedWebsitesByUserId(UserId creatorId) {
    LOG.debug("Method findSubscribedWebsitesByUserId called");
    return List.of(websiteList.get(0), websiteList.get(1));
  }

  @Override
  public List<Website> findUnSubscribedWebsitesByUserId(UserId creatorId) {
    LOG.debug("Method findUnSubscribedWebsitesByUserId called");
    return List.of(websiteList.get(2));
  }

  @Override
  public void update(Website website) {
    LOG.debug("Method update called");
    for (Website website1 : websiteList) {
      if (website1.id().equals(website.id())) {
        return;
      }
    }
    throw new WebsiteNotFoundException("Website is not found with id = " + website.id().getValue());
  }

  @Override
  public void delete(WebsiteId id, UserId userId) {
    LOG.debug("Method delete called");
    for (Website website1 : websiteList) {
      if (website1.id().equals(id)) {
        return;
      }
    }
    throw new WebsiteNotFoundException("Website is not found with id = " + id.getValue());
  }

  @Override
  public void updateSubscribedWebsites(List<WebsiteId> websites, UserId userId) {
    LOG.debug("Method updateSubscribedWebsites called");
  }
}