package com.svalero.audioclub.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Disco {
    private int id;
    private String name;
    private String description;
    private Date datetime;
    private float price;
    private String picture;
}
