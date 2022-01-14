package com.zikozee.flywayjdbctemplate.movie;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

@Repository
public class MovieRepository implements MovieDao{

    @Override
    public List<Movie> selectMovies() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int insertMovie(Movie movie) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int deleteMovie(int id) {
        throw new UnsupportedOperationException("not implemented");

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        throw new UnsupportedOperationException("not implemented");
    }
}
