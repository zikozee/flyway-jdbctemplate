package com.zikozee.flywayjdbctemplate.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

@RestController
@RequestMapping(path = "api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> listMovies() {
        return movieService.getMovies();
    }

    @GetMapping(path = "pagedMovies")
    public PageImpl<Movie> pagedMovies(final Pageable pageable) {
        return movieService.getPagedMovies(pageable);
    }

    @GetMapping("{id}")
    public Movie getMovieId(@PathVariable("id") Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        movieService.addNewMovie(movie);
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
    }

    // TODO: Update movie
    @PutMapping(path = "{name}/{id}")
    public String updateMovie(@PathVariable("name") String name, @PathVariable("id") int id) {
        return movieService.updateMovie(name, id);
    }
}