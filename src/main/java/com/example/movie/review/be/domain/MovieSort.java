package com.example.movie.review.be.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieSort {
  POPULAR("popular", "popularity.desc"),
  RATING("rating", null),
  RELEASE_DATE("release_date", "primary_release_date.desc");

  private final String name;
  private final String tmdbName;
}
