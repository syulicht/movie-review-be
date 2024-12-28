package com.example.movie.review.be.domain;

import lombok.Value;

@Value
public class MovieSummary {
  Integer id;

  String title;

  String backdropUrl;

  Double rating;
}
