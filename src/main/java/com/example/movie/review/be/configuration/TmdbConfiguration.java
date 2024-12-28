package com.example.movie.review.be.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tmdb")
public class TmdbConfiguration {
  private String apiAccessToken;
}
