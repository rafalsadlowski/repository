package com.empik.repository.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.empik.repository.Application;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RepositoryControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Test
  public void shouldGetCalculationsBeanUserObject() throws Exception {
    //given
    String user = "octocat";
    //when
    //then
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users/"+user, String.class)).contains(user);
  }

}
