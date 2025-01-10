package com.example.movie.review.be.service;

import com.example.movie.review.be.domain.MovieDetail;
import com.example.movie.review.be.domain.MovieSummary;
import com.example.movie.review.be.domain.Review;
import com.example.movie.review.be.domain.tmdb.TmdbMovieDetail;
import com.example.movie.review.be.repository.ReviewRepository;
import com.example.movie.review.be.repository.TmdbRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
  private final TmdbRepository tmdbRepository;
  private final ReviewRepository reviewRepository;

  public List<MovieSummary> getRecommendedMovies() {
    final var tmdbMovieDiscoverResult = tmdbRepository.getRecommendedMovies();

    return tmdbMovieDiscoverResult.getResults().subList(0, 10).stream()
        .map(m -> m.toMovieSummary(5.0))
        .toList();
  }

  public MovieDetail getMovieDetail(Integer movieId) {
    final TmdbMovieDetail detail = tmdbRepository.getMovieDetail(movieId);
    final var reviews = reviewRepository.findByMovieId(movieId);
    final var averageReviewsOptional =
        reviews.stream().map(Review::getRating).mapToInt(Integer::intValue).average();
    final var averageReviews =
        averageReviewsOptional.isPresent() ? averageReviewsOptional.getAsDouble() : 0;
    return detail.toMovieDetail(averageReviews, reviews);
  }

  public Pair<List<MovieSummary>, Integer> searchMovies(String query, Integer page) {
    final var tmdbMovieDiscoverResult = tmdbRepository.searchMovies(query, page);

    return Pair.of(
        tmdbMovieDiscoverResult.getResults().stream().map(m -> m.toMovieSummary(5.0)).toList(),
        tmdbMovieDiscoverResult.getTotalResults());
  }
}
