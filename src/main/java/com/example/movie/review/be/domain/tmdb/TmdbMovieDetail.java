package com.example.movie.review.be.domain.tmdb;

import com.example.movie.review.be.domain.Genre;
import com.example.movie.review.be.domain.MovieDetail;
import com.example.movie.review.be.domain.Review;
import com.example.movie.review.be.util.ProductionCountryConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieDetail {
  Integer id;

  String title;

  String overview;

  @JsonProperty("backdrop_path")
  String backdropPath;

  @JsonProperty("poster_path")
  String posterPath;

  @JsonProperty("release_date")
  String releaseDate;

  @JsonProperty("production_countries")
  List<ProductionCountry> productionCountries;

  List<Genre> genres;

  Integer runtime;

  public MovieDetail toMovieDetail(Double rating, List<Review> reviews) {
    return new MovieDetail(
        id,
        title,
        overview,
        "https://image.tmdb.org/t/p/original" + backdropPath,
        "https://image.tmdb.org/t/p/original" + posterPath,
        releaseDate,
        productionCountries.stream()
            .map(country -> ProductionCountryConverter.countryMap.getOrDefault(country.iso3166, ""))
            .filter(country -> !country.isEmpty())
            .toList(),
        genres,
        runtime,
        rating,
        reviews);
  }

  @Value
  public static class ProductionCountry {
    @JsonProperty("iso_3166_1")
    String iso3166;

    String name;
  }
}
