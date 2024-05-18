package com.svalero.audioclub.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Cliente {

    private int id_cliente;
    private String dni;
    private String nombre;
    private String email;
    private String password;
    private String role;
}
