package com.svalero.audioclub.dao;

import com.svalero.audioclub.domain.Disco;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public class DiscoDao {


    @SqlQuery("SELECT * FROM discos")
    @UseRowMapper(DiscoMapper.class)
    List<Disco> getAllDiscos();
}
