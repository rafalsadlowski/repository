package com.empik.repository.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.empik.repository.constants.RepositoryHttpResponse;
import com.empik.repository.dto.CalculationsBean;
import com.empik.repository.dto.GithubRepositoryBean;
import com.empik.repository.exception.CalculationException;
import com.empik.repository.exception.GithubUserException;
import com.empik.repository.service.UserLogService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class RepositoryController {

  private Logger log = Logger.getLogger(RepositoryController.class.getName());

  @Autowired
  private UserLogService taskService;

  /**
   * Get calculations with github repository request log 
   * http://localhost:8080/swagger-ui.html#
   * 
   * @param login - github login  
   * @return {@link CalculationsBean} object with algorithm with the calculation "6 / liczba_followers * (2 + liczba_public_repos)"
   */

  @ApiResponses({ 
    @ApiResponse(code = RepositoryHttpResponse.GITHUB_RESPONSE_INVALID, message = "Invalid github response"),
    @ApiResponse(code = RepositoryHttpResponse.CALCULATION_INVALID, message = "Algorithm calculations mismatch") 
   })

  @RequestMapping(value = "/{login}", method = RequestMethod.GET)
  public ResponseEntity<CalculationsBean> getCalculationsBean(@PathVariable String login) {
    log.fine("Get calculations for login: " + login);
    taskService.incrementDBRequestCount(login); // every request log
    try {
      GithubRepositoryBean githubRepositoryBean = taskService.getGithubRequest(login);
      CalculationsBean calculationsBean = taskService.getCalculatationsBean(githubRepositoryBean);
      return ResponseEntity.ok(calculationsBean);
    }
    catch (GithubUserException ex) {
      return ResponseEntity.status(RepositoryHttpResponse.GITHUB_RESPONSE_INVALID).build();
    }
    catch (CalculationException ex) {
      return ResponseEntity.status(RepositoryHttpResponse.CALCULATION_INVALID).build();
    }
  }

}