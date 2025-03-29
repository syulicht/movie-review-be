package com.example.movie.review.be.controller;

import com.example.movie.review.be.domain.UserWithoutCredential;
import com.example.movie.review.be.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
  UserService userService;

  @GetMapping("")
  public ResponseEntity<Object> getAccountInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();
    Optional<UserWithoutCredential> user = userService.getUserWithoutCredential(userId);
    if (user.isEmpty()) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
}
