package com.zikozee.flywayjdbctemplate.movie;

import com.zikozee.flywayjdbctemplate.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieDao movieDao;

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public void addNewMovie(Movie movie) {
        // TODO: check if movie exists
        int result = movieDao.insertMovie(movie);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteMovie(Integer id) {
        Optional<Movie> movies = movieDao.selectMovieById(id);
        movies.ifPresentOrElse(movie -> {
            int result = movieDao.deleteMovie(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete movie");
            }
        }, () -> {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        });
    }

    public Movie getMovie(int id) {
        return movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
    }

    public PageImpl<Movie> getPagedMovies(Pageable pageable) {
        return movieDao.getPagedMovies(pageable);
    }

    public String updateMovie(String name, int id) {
        int result = movieDao.updateMovie(name, id);
        if(result >0) return "Update Successful";
        else throw new NotFoundException(String.format("Movie with name %s not found", name));
    }
}