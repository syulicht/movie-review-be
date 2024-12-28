package com.example.movie.review.be.domain.tmdb;

import com.example.movie.review.be.domain.MovieSummary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovie {
  Integer id;

  @JsonProperty("backdrop_path")
  String backdropPath;

  String title;

  public MovieSummary toMovieSummary(Double rating) {
    return new MovieSummary(
        id, title, "https://image.tmdb.org/t/p/original" + backdropPath, rating);
  }
}
