package com.svalero.audioclub.dao;

import com.svalero.audioclub.domain.Cliente;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;


import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements RowMapper<Cliente> {

    @Override
    public Cliente map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Cliente(rs.getInt("id_cliente"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role"));
    }


}
