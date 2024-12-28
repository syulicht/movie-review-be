package com.example.movie.review.be.service;

import com.example.movie.review.be.domain.MovieSummary;
import com.example.movie.review.be.repository.TmdbRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
  private final TmdbRepository tmdbRepository;

  public List<MovieSummary> getRecommendedMovies() {
    final var tmdbMovieDiscoverResult = tmdbRepository.getRecommendedMovies();

    return tmdbMovieDiscoverResult.getResults().subList(0, 10).stream()
        .map(m -> m.toMovieSummary(5.0))
        .toList();
  }
}
