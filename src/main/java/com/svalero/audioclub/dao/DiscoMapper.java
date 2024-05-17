package com.svalero.audioclub.dao;

import com.svalero.audioclub.domain.Disco;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DiscoMapper implements RowMapper<Disco> {

    @Override
    public Disco map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Disco(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDate("datetime"),
                rs.getFloat("price"),
                rs.getString("picture"));
    }
}
