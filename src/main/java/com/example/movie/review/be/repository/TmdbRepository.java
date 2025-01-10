package com.example.movie.review.be.repository;

import com.example.movie.review.be.domain.tmdb.TmdbMovieDetail;
import com.example.movie.review.be.domain.tmdb.TmdbMovieDiscoverResult;

public interface TmdbRepository {
  TmdbMovieDiscoverResult getRecommendedMovies();

  TmdbMovieDetail getMovieDetail(Integer movieId);

  TmdbMovieDiscoverResult searchMovies(String query, Integer page);
}
