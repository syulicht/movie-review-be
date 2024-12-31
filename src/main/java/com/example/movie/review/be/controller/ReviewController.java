package com.example.movie.review.be.controller;

import com.example.movie.review.be.domain.ReviewLimitedWithContentAndRating;
import com.example.movie.review.be.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  @PostMapping("/{movieId}")
  public ResponseEntity<Void> addReview(
      @PathVariable Integer movieId, @RequestBody ReviewLimitedWithContentAndRating review) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();
    reviewService.addReview(userId, movieId, review);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
