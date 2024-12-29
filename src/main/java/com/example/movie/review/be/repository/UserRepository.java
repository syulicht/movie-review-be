package com.example.movie.review.be.repository;

import com.example.movie.review.be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);
}
