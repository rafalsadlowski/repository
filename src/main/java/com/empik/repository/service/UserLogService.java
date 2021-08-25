package com.empik.repository.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.empik.repository.bean.UserLogEntity;
import com.empik.repository.data.UserLogRepository;
import com.empik.repository.dto.CalculationsBean;
import com.empik.repository.dto.GithubRepositoryBean;
import com.empik.repository.exception.CalculationException;
import com.empik.repository.exception.GithubUserException;

@Service
public class UserLogService {

  private final static Logger log = Logger.getLogger(UserLogService.class.getName());
  private final static RestTemplate restTemplate = new RestTemplate();
  private final static String GITHUB_REQEST_URL = "https://api.github.com/users/";

  @Autowired
  private UserLogRepository userLogRepository;

  /**
   * Increment user request count by login
   * 
   * @param login - user login 
   * @return 
   */
  public synchronized UserLogEntity incrementDBRequestCount(String login) {
    UserLogEntity userLogEntity = getuserLogEntity(login);
    userLogEntity.setRequestCount(userLogEntity.getRequestCount() + 1);
    try {
      return userLogRepository.save(userLogEntity);
    }
    catch (Exception e) {
      log.severe("Increment request count save " + e.getMessage());
    }
    return null;
  }
  
  /**
   * Get repository bean
   * 
   * @param login - github repository login  
   * @return {@link GithubRepositoryBean}  - github repository object  
   * @throws GithubUserException - external service exception
   */

  public GithubRepositoryBean getGithubRequest(String login) throws GithubUserException {
    GithubRepositoryBean githubRepositoryBean = getRepositoryBean(login);
    if (githubRepositoryBean == null || githubRepositoryBean.getLogin() == null) {
      throw new GithubUserException("Github reqest is empty");
    }
    return githubRepositoryBean;
  }

  /**
   * Get calculations for github repository 
   * 
   * @param login - github repository login  
   * @return {@link CalculationsBean} object with algorithm with the calculations
   * algo:  "6 / liczba_followers * (2 + liczba_public_repos)"
   * @throws CalculationException - exception check calculation(ex. div 0)
   */

  public CalculationsBean getCalculatationsBean(GithubRepositoryBean githubRepositoryBean) throws CalculationException {
    CalculationsBean calculationsBean = new CalculationsBean();
    try {
      calculationsBean.setAvatarUrl(githubRepositoryBean.getAvatarUrl());
      calculationsBean.setCreatedAt(githubRepositoryBean.getCreatedAt());
      calculationsBean.setId(githubRepositoryBean.getId());
      calculationsBean.setLogin(githubRepositoryBean.getLogin());
      calculationsBean.setName(githubRepositoryBean.getName());
      calculationsBean.setType(githubRepositoryBean.getType());
      
      Float calculations = calculate(githubRepositoryBean);
      calculationsBean.setCalculations(calculations);
    }
    catch (CalculationException exception) {
      log.severe(exception.getMessage());
      throw exception;
    }
    return calculationsBean;
  }


  private GithubRepositoryBean getRepositoryBean(String login) {
    String githubReq = createRequestUrl(login);
    try {
      GithubRepositoryBean githubRepositoryBean = restTemplate.getForObject(githubReq, GithubRepositoryBean.class);
      return githubRepositoryBean;
    }
    catch (Throwable e) {
      log.warning("RestTemplate equest exception: " + e.getMessage());
    }
    return null;
  }

  private UserLogEntity getuserLogEntity(String login) {
    UserLogEntity userLogEntity = null;
    Optional<UserLogEntity> bean = userLogRepository.findById(login);
    if (bean.isEmpty()) {
      log.info("login first request " + login);
      userLogEntity = new UserLogEntity(login);
    }
    else {
      userLogEntity = bean.get();
    }
    return userLogEntity;
  }

  private Float calculate(GithubRepositoryBean githubRepositoryBean) throws CalculationException {
    // check before calculation
    if (githubRepositoryBean.getPublicRepos() == null) {
      throw new CalculationException("Public repos is null");
    }
    if (githubRepositoryBean.getFollowers() == null) {
      throw new CalculationException("Followers is null");
    }
    if (githubRepositoryBean.getFollowers().equals(0)) {
      throw new CalculationException("CalculateViews div by 0");
    }
    // calculate 
    Float calculations = (float)(6 / githubRepositoryBean.getFollowers() * (2 + githubRepositoryBean.getPublicRepos()));
    return calculations;
  }

  private String createRequestUrl(String login) {
    return GITHUB_REQEST_URL + login;
  }

}
