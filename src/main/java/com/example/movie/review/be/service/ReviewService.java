package com.example.movie.review.be.service;

import com.example.movie.review.be.domain.Review;
import com.example.movie.review.be.domain.ReviewLimitedWithContentAndRating;
import com.example.movie.review.be.domain.UserWithoutCredential;
import com.example.movie.review.be.repository.ReviewRepository;
import com.example.movie.review.be.repository.UserWithoutCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final UserWithoutCredentialRepository userWithoutCredentialRepository;

  public void addReview(String userId, Integer movieId, ReviewLimitedWithContentAndRating review) {
    try {
      final var userOptional = userWithoutCredentialRepository.findById(Integer.parseInt(userId));
      final UserWithoutCredential user =
          userOptional.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
      Review newReview = new Review(review.getContent(), review.getRating(), user, movieId);
      reviewRepository.save(newReview);
    } catch (Exception e) {
      throw e;
    }
  }
}
