package com.empik.repository;

import java.util.TimeZone;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

  private static Logger log = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
    SpringApplication.run(Application.class, args);
    log.info("Repository Application Start");
  }

  @Bean
  public Docket api() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    return docket;
  }

}