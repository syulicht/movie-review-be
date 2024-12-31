package com.example.movie.review.be.repository;

import com.example.movie.review.be.configuration.TmdbConfiguration;
import com.example.movie.review.be.domain.tmdb.TmdbMovieDetail;
import com.example.movie.review.be.domain.tmdb.TmdbMovieDiscoverResult;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
@RequiredArgsConstructor
public class TmdbRepositoryImpl implements TmdbRepository {
  private final TmdbConfiguration tmdbConfiguration;
  private final RestTemplate restTemplate;

  @Override
  public TmdbMovieDiscoverResult getRecommendedMovies() {
    final var url =
        UriComponentsBuilder.fromUriString("https://api.themoviedb.org/3/discover/movie")
            .queryParam("language", "ja")
            .queryParam("region", "JP")
            .queryParam("release_date.gte", LocalDate.now().minusYears(1))
            .build()
            .toUriString();

    final var header = new HttpHeaders();
    header.setAccept(List.of(MediaType.APPLICATION_JSON));
    header.add("Authorization", "Bearer " + tmdbConfiguration.getApiAccessToken());
    final var entity = new HttpEntity<>(null, header);

    final var result =
        restTemplate.exchange(url, HttpMethod.GET, entity, TmdbMovieDiscoverResult.class);

    return result.getBody();
  }

  @Override
  public TmdbMovieDetail getMovieDetail(Integer movieId) {
    final var url =
        UriComponentsBuilder.fromUriString("https://api.themoviedb.org/3/movie/{movieId}")
            .queryParam("language", "ja")
            .buildAndExpand(movieId)
            .toUriString();

    final var header = new HttpHeaders();
    header.setAccept(List.of(MediaType.APPLICATION_JSON));
    header.add("Authorization", "Bearer " + tmdbConfiguration.getApiAccessToken());
    final var entity = new HttpEntity<>(null, header);

    final var result = restTemplate.exchange(url, HttpMethod.GET, entity, TmdbMovieDetail.class);
    return result.getBody();
  }
}
