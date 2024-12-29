package com.example.movie.review.be.controller;

import com.example.movie.review.be.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}

  public record SignUpRequest(
      @NotBlank String name, @NotBlank @Email String email, @NotBlank String password) {}

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    try {
      String token = authService.loginUser(request.email, request.password);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("Authorization", token);
      return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/signUp")
  public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
    String token = authService.signUpUser(request.name, request.email, request.password);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Authorization", token);
    return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
  }
}
