package com.example.movie.review.be.domain;

import lombok.Value;

@Value
public class ReviewLimitedWithContentAndRating {
  String content;
  Integer rating;
}
