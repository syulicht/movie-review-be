package com.example.movie.review.be.repository;

import com.example.movie.review.be.domain.UserWithoutCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWithoutCredentialRepository
    extends JpaRepository<UserWithoutCredential, Integer> {}
