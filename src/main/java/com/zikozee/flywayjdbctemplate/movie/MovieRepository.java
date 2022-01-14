package com.zikozee.flywayjdbctemplate.movie;

import com.zikozee.flywayjdbctemplate.actor.Actor;
import com.zikozee.flywayjdbctemplate.actor.ActorRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieRepository implements MovieDao {

    //todo info: to modify use update

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> selectMovies() {
        var sql = """
                SELECT id, name, release_date 
                FROM movie 
                LIMIT 100;
                """;
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper());

        return movies.stream()
                .map(movie -> new Movie(movie.id(), movie.name(), getActors(movie.id()), movie.releaseDate()))
                .collect(Collectors.toList());
    }

    @Override
    public int insertMovie(Movie movie) {
        var sql = """
                INSERT INTO movie(name, release_date) 
                VALUES(?,?);
                """;
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        var sql = """
                DELETE FROM movie 
                WHERE id = ?;
                """;

        return jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        var sql = """
                SELECT id, name, release_date 
                FROM movie 
                WHERE id=?;
                """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), id)
                .stream().findFirst()
                .map(movie -> new Movie(movie.id(), movie.name(), getActors(movie.id()), movie.releaseDate()));
    }

    @Override
    public PageImpl<Movie> getPagedMovies(Pageable pageable) {
//        select *  from public."Album" order by "AlbumId" offset 300 limit 1
        Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Order.by("id");

        var sql = """
                SELECT id, name, release_date 
                FROM movie
                """;

        var pagedQuery = sql + " ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();

        log.info("PAGED_QUERY: {}", pagedQuery);
        List<Movie> pagedMovies = jdbcTemplate.query(pagedQuery, new MovieRowMapper());
        return new PageImpl<>(pagedMovies, pageable, pagedMovies.size());
    }

    @Override
    public int updateMovie(String name, int id) {
        var sql = """
                UPDATE movie set name = ? where id = ?
                """;
        return jdbcTemplate.update(sql, name, id);
    }

    private List<Actor> getActors(int movieId){
        var sql = """
                SELECT id, name 
                FROM actor 
                where movie = ?
                """;

        return jdbcTemplate.query(sql, new ActorRowMapper(), movieId);
    }
}
