package com.example.movie.review.be.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;
}
