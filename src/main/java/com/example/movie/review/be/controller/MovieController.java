package com.example.movie.review.be.controller;

import com.example.movie.review.be.domain.MovieDetail;
import com.example.movie.review.be.domain.response.RecommendedMovieResponse;
import com.example.movie.review.be.domain.response.SearchMoviesResponse;
import com.example.movie.review.be.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
  private final MovieService movieService;

  @GetMapping("/recommended")
  public RecommendedMovieResponse recommended() {
    return new RecommendedMovieResponse(movieService.getRecommendedMovies());
  }

  @GetMapping("/{movieId}")
  public MovieDetail movieDetail(@PathVariable Integer movieId) {
    return movieService.getMovieDetail(movieId);
  }

  @GetMapping("/search")
  public SearchMoviesResponse search(
      @RequestParam(required = false) String query, @RequestParam(required = false) Integer page) {
    final var searchMovieResult =
        movieService.searchMovies(query == null ? "" : query, page == null ? 1 : page);

    return new SearchMoviesResponse(
        page, searchMovieResult.getSecond(), searchMovieResult.getFirst());
  }
}
