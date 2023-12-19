package com.example.as211bereport.domain.model.funcionaryTeen;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class TeenResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    private Integer id_adolescente;
    private String name;
    private String surnamefather;
    private String surnamemother;
    private String dni;
    private String estado;
}
