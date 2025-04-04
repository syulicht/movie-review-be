package com.example.movie.review.be.service;

import com.example.movie.review.be.domain.LoginUserDetails;
import com.example.movie.review.be.domain.User;
import com.example.movie.review.be.domain.UserWithoutCredential;
import com.example.movie.review.be.repository.UserRepository;
import com.example.movie.review.be.repository.UserWithoutCredentialRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final UserWithoutCredentialRepository userWithoutCredential;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> maybeUser = Optional.ofNullable(userRepository.findByEmail(email));
    return maybeUser
        .map(LoginUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("user not found."));
  }

  public Optional<UserWithoutCredential> getUserWithoutCredential(String id) {
    return userWithoutCredential.findById(Integer.valueOf(id));
  }
}
