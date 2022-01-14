package com.zikozee.flywayjdbctemplate.movie;

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
    // TODO: Update
}
