package com.svalero.audioclub.dao;

import com.svalero.audioclub.domain.Cliente;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public interface ClienteDao {
    @SqlQuery("SELECT * FROM clientes")
    @UseRowMapper(ClienteMapper.class)
    List<Cliente> getAllClientes();

    @SqlQuery("SELECT * FROM clientes WHERE id_cliente = ?")
    @UseRowMapper(ClienteMapper.class)
    Cliente getCliente(int id_cliente);

    @SqlQuery("SELECT * FROM clientes WHERE dni = ? AND password = SHA1(?)")
    @UseRowMapper(ClienteMapper.class)
    Cliente getCliente(String dni, String password);

    @SqlUpdate("INSERT INTO clientes (dni, nombre, email, password, role) VALUES (?, ?, ?, SHA1(?), ?)")
    int addCliente(String dni, String nombre, String email, String password, String role);

    @SqlUpdate("UPDATE clientes SET dni = ?, nombre = ?, email = ?, role = ? WHERE id_cliente = ?")
    int updateCliente(String dni, String nombre, String email, String role, int id_cliente);

    @SqlUpdate("DELETE FROM clientes WHERE id_cliente = ?")
    int removeCliente(int id_cliente);

}
