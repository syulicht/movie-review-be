package com.example.movie.review.be.domain;

import java.util.List;
import lombok.Value;

@Value
public class MovieDetail {
  Integer id;

  String title;

  String overview;

  String backdropUrl;

  String posterUrl;

  String releaseDate;

  List<String> productionCountries;

  List<Genre> genres;

  Integer runtime;

  Double rating;

  List<Review> reviews;
}
