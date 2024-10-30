package com.example.theater.repositories;

import com.example.theater.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository < Movie, Long > {
    Movie findByTitle ( String title );

    List < Movie > getAllMoviesByNowShowing ( boolean nowShowing );

    @Query("select m from Movie m where (m.title like concat('%', :keyword, '%') or " +
            "m.director like concat('%', :keyword, '%') or " +
            "m.genre like concat('%', :keyword, '%') or " +
            "m.actors like concat('%', :keyword, '%') or " +
            "m.language like concat('%', :keyword, '%')) " +
            "and m.nowShowing = :nowShowing")
    List<Movie> getAllMoviesByKeyWordAndNowShowing(String keyword, boolean nowShowing);

    @Query("select m from Movie m where m.genre like concat('%', :genre, '%') and m.nowShowing = :nowShowing")
    List<Movie> getAllMoviesByGenreAndNowShowing(String genre, boolean nowShowing);
}
