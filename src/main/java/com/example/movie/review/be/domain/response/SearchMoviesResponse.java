package com.example.movie.review.be.domain.response;

import com.example.movie.review.be.domain.MovieSummary;
import java.util.List;
import lombok.Value;

@Value
public class SearchMoviesResponse {
  Integer page;

  Integer count;

  List<MovieSummary> movies;
}
