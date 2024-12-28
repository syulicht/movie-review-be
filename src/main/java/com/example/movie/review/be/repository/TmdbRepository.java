package com.example.movie.review.be.repository;

import com.example.movie.review.be.domain.tmdb.TmdbMovieDiscoverResult;

public interface TmdbRepository {
  TmdbMovieDiscoverResult getRecommendedMovies();
}
