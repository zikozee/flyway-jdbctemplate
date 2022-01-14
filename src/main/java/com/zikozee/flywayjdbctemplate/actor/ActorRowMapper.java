package com.zikozee.flywayjdbctemplate.actor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author : Ezekiel Eromosei
 * @created : 14 Jan, 2022
 */

public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Actor(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
