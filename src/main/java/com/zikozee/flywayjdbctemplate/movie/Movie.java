package com.zikozee.flywayjdbctemplate.movie;

import com.zikozee.flywayjdbctemplate.actor.Actor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

public record Movie(Integer id,
                    String name,
                    List<Actor> actors,
                    LocalDate releaseDate) {
}