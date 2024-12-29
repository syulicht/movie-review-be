package com.example.movie.review.be.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.movie.review.be.configuration.HmacConfiguration;
import com.example.movie.review.be.domain.LoginUserDetails;
import com.example.movie.review.be.domain.User;
import com.example.movie.review.be.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final DaoAuthenticationProvider daoAuthenticationProvider;
  private final HmacConfiguration hmacConfiguration;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> maybeUser = Optional.ofNullable(userRepository.findByEmail(email));
    return maybeUser
        .map(LoginUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("user not found."));
  }

  public String signUpUser(String name, String email, String password) {
    User user = new User(null, name, email, passwordEncoder.encode(password));
    userRepository.save(user);
    return JWT.create()
        .withClaim("userid", user.getId())
        .sign(Algorithm.HMAC256(hmacConfiguration.getSecret()));
  }

  public String loginUser(String email, String password) {
    var authentication =
        daoAuthenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));
    return JWT.create()
        .withClaim("userid", ((LoginUserDetails) authentication.getPrincipal()).getUserId())
        .sign(Algorithm.HMAC256(hmacConfiguration.getSecret()));
  }
}
