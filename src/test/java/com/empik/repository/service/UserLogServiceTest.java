package com.empik.repository.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.empik.repository.bean.UserLogEntity;
import com.empik.repository.data.UserLogRepository;
import com.empik.repository.dto.CalculationsBean;
import com.empik.repository.dto.GithubRepositoryBean;
import com.empik.repository.exception.CalculationException;
import com.empik.repository.exception.GithubUserException;

@SpringBootTest
public class UserLogServiceTest {
  
  @Autowired
  UserLogService userLogService;

  @Autowired
  UserLogRepository userLogRepository;

  @Test
  public void contextLoads() throws Exception {
    assertThat(userLogRepository).isNotNull();
    assertThat(userLogService).isNotNull();
  }

  @Test
  public void shouldGetCalculationsIncrementAccess() {
    //given
    String login = "testUser";
    //when
    userLogService.incrementDBRequestCount(login);
    UserLogEntity userLogin = userLogService.incrementDBRequestCount(login);
    //then   
    assertEquals(userLogin.getRequestCount(), Integer.valueOf(2));
  }

  public void shouldCalculateAlgorithmCalcExample() throws CalculationException, GithubUserException {
    //given 
    int followers = 10;
    int publicRepos = 2;

    GithubRepositoryBean githubRepositoryBean = new GithubRepositoryBean();
    githubRepositoryBean.setFollowers(followers);
    githubRepositoryBean.setPublicRepos(publicRepos);
    //when
    CalculationsBean calculationsBean = userLogService.getCalculatationsBean(githubRepositoryBean);
    //then 
    assertEquals(calculationsBean.getCalculations(), 6 / followers * (2 + publicRepos));
  }

}
