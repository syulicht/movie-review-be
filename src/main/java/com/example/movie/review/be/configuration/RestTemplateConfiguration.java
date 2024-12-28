package com.example.movie.review.be.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    return restTemplateBuilder.build();
  }
}
