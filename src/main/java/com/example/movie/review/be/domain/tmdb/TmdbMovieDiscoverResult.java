package com.example.movie.review.be.domain.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieDiscoverResult {
  Integer page;

  List<TmdbMovie> results;

  @JsonProperty("total_results")
  Integer totalResults;
}
