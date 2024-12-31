package com.example.movie.review.be.repository;

import com.example.movie.review.be.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
  List<Review> findByMovieId(Integer movieId);
}
