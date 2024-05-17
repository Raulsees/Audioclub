package com.svalero.audioclub.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Disco {
    private int id;
    private String nombre;
    private int año;
    private String genero;
    private String picture;
}
