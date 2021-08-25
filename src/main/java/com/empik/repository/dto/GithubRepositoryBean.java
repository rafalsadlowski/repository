package com.empik.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GithubRepositoryBean extends CalculationsBean { 
  
  @JsonProperty("node_id")
  public String nodeId; 
  
  @JsonProperty("gravatar_id")
  public String gravatarId;
  
  @JsonProperty("url")
  public String url;
  
  @JsonProperty("html_url")
  public String htmlUrl;
  
  @JsonProperty("followers_url")
  public String followersUrl;
  
  @JsonProperty("following_url")
  public String followingUrl;
  
  @JsonProperty("gists_url")
  public String gistsUrl;
  
  @JsonProperty("starred_url")
  public String starredUrl;
  
  @JsonProperty("subscriptions_url")
  public String subscriptionsUrl;
  
  @JsonProperty("organizations_url")
  public String organizationsUrl;
  
  @JsonProperty("repos_url")
  public String reposUrl;
  
  @JsonProperty("events_url")
  public String eventsUrl;
  
  @JsonProperty("received_events_url")
  public String receivedEventsUrl; 
  
  @JsonProperty("site_admin")
  public Boolean siteAdmin; 
  
  @JsonProperty("company")
  public String company;
  
  @JsonProperty("blog")
  public String blog;
  
  @JsonProperty("location")
  public String location;
  
  @JsonProperty("public_repos") 
  public Integer publicRepos;

  @JsonProperty("public_gists")
  public Integer publicGists;
  
  @JsonProperty("followers")
  public Integer followers;
  
  @JsonProperty("following")
  public Integer following; 
  
  @JsonProperty("updated_at")
  public String updatedAt;
}
