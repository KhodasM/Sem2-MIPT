package com.example.newsrecommendation.models.website;

import com.example.newsrecommendation.models.user.UserId;

import java.util.Objects;

public record Website(WebsiteId id, String url, String description, UserId creatorId) {
  public static final Website WEBSITE_1 = new Website(new WebsiteId(1L), "1", "1", new UserId(1L));
  public static final Website WEBSITE_2 = new Website(new WebsiteId(2L), "2", "2", new UserId(2L));
  public static final Website WEBSITE_3 = new Website(new WebsiteId(3L), "3", "3", new UserId(3L));

  public Website initializeWithId(WebsiteId newId) {
    return new Website(newId, url, description, creatorId);
  }

  public Website withUrl(String newUrl) {
    return new Website(id, newUrl, description, creatorId);
  }

  public Website withDescription(String newDescription) {
    return new Website(id, url, newDescription, creatorId);
  }

  public Website withCreatorId(UserId newCreatorId) {
    return new Website(id, url, description, newCreatorId);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (!(object instanceof Website website)) {
      return false;
    }

    return id != null && id.equals(website.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}