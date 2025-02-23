package com.example.newsrecommendation.controllers;

import com.example.newsrecommendation.models.user.UserId;
import com.example.newsrecommendation.models.website.Website;
import com.example.newsrecommendation.models.website.WebsiteId;
import com.example.newsrecommendation.models.website.WebsiteInfo;
import com.example.newsrecommendation.models.website.WebsiteService;
import com.example.newsrecommendation.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.newsrecommendation.models.website.exceptions.WebsiteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/websites")
public class WebsiteController {
  private final WebsiteService websiteService;
  private static final Logger LOG = LoggerFactory.getLogger(WebsiteController.class);

  public WebsiteController(WebsiteService websiteService) {
    this.websiteService = websiteService;
  }

  @GetMapping("/{id}")
  private ResponseEntity<Website> get(@PathVariable Long id) throws WebsiteNotFoundException {
    Website website = websiteService.findById(new WebsiteId(id));
    LOG.debug("Website found by id = {}",id);
    return ResponseEntity.ok(website);
  }

  @PutMapping
  private ResponseEntity<String> put(@RequestBody Website website) throws WebsiteNotFoundException{
    websiteService.update(website.id(),website.url(), website.description());
    LOG.debug("Successfully updated website with id = {}",website.id());
    return ResponseEntity.ok("Website updated");
  }

  @GetMapping("/user/{id}")
  private ResponseEntity<List<Website>> getUsersWebsites(@PathVariable Long id){
    List<Website> websites = websiteService.getSubscribedWebsitesByUserId(new UserId(id));
    LOG.debug("Websites found by user id = {}", id);
    return ResponseEntity.ok(websites);
  }

  @PostMapping("/user")
  private ResponseEntity<Website> create(@RequestBody WebsiteInfo websiteInfo) throws WebsiteAlreadyExistsException {
    Website website = websiteService.create(new Website(new WebsiteId(null), websiteInfo.url(), websiteInfo.description(), new UserId(websiteInfo.userId())));
    LOG.debug("Successfully created website");
    return ResponseEntity.ok(website);
  }

  @DeleteMapping("/{userId}/{websiteId}")
  private ResponseEntity<String> delete(@PathVariable Long userId, @PathVariable Long websiteId) throws WebsiteNotFoundException{
    websiteService.delete(new WebsiteId(websiteId), new UserId(userId));
    LOG.debug("Successfully deleted website with id = {} by user with id = {}",websiteId,userId);
    return ResponseEntity.ok("Website deleted");
  }
}

