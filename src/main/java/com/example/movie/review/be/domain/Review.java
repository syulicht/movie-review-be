package com.example.movie.review.be.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String content;

  private Integer rating;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserWithoutCredential user;

  private Integer movieId;

  public Review(String content, Integer rating, UserWithoutCredential user, Integer movieId) {
    this.content = content;
    this.rating = rating;
    this.user = user;
    this.movieId = movieId;
  }
}
