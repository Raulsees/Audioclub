package com.svalero.audioclub.dao;

import com.svalero.audioclub.domain.Disco;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;


import java.util.List;

public interface DiscoDao {


    @SqlQuery("SELECT * FROM discos")
    @UseRowMapper(DiscoMapper.class)
    List<Disco> getAllDiscos();

    @SqlUpdate("INSERT INTO discos (nombre, ano, genero, picture) VALUES (?, ?, ?, ?)")
    int addDisco(String nombre, int ano, String genero, String picture);

    @SqlUpdate("DELETE FROM discos WHERE id_disco = ?")
    int removeDisco(int id_disco);
}
