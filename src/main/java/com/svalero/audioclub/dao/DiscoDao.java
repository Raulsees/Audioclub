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

    @SqlQuery("SELECT * FROM discos WHERE id_disco = ?")
    @UseRowMapper(DiscoMapper.class)
    Disco getDisco(int id_disco);

    @SqlUpdate("INSERT INTO discos (nombre, ano, genero, picture) VALUES (?, ?, ?, ?)")
    int addDisco(String nombre, int ano, String genero, String picture);

    @SqlUpdate("UPDATE discos SET nombre = ?, ano = ?, genero = ?, picture = ?) WHERE id = ?")
    int updateDisco(String nombre, int ano, String genero, String picture, int id_disco);


    @SqlUpdate("DELETE FROM discos WHERE id_disco = ?")
    int removeDisco(int id_disco);



}
