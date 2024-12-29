package com.example.movie.review.be.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.movie.review.be.configuration.HmacConfiguration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthorizeFilter extends OncePerRequestFilter {
  private final HmacConfiguration hmacConfiguration;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String authToken = request.getHeader("Authorization");
    if (authToken == null || !authToken.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    DecodedJWT decodedJwt =
        JWT.require(Algorithm.HMAC256(hmacConfiguration.getSecret()))
            .build()
            .verify(authToken.substring(7));
    String userId = decodedJwt.getClaim("userid").toString();
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, List.of()));
    filterChain.doFilter(request, response);
  }
}
