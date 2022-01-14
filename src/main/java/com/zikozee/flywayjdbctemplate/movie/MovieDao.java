package com.zikozee.flywayjdbctemplate.movie;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

public interface MovieDao {
    List<Movie> selectMovies();
    int insertMovie(Movie movie);
    int deleteMovie(int id);
    Optional<Movie> selectMovieById(int id);

    PageImpl<Movie> getPagedMovies(Pageable pageable);

    int updateMovie(String name, int id);
    // TODO: Update
}
