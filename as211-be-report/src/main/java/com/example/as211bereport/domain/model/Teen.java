package com.example.as211bereport.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teen {

    private Integer id_adolescente;
    private String name;
    private String surnamefather;
    private String surnamemother;
    private String dni;
    private String estado;

    public Teen(String name, String surnamefather, String surnamemother, String dni, String estado) {
        this.name = name;
        this.surnamefather = surnamefather;
        this.surnamemother = surnamemother;
        this.dni = dni;
        this.estado = estado;
    }

    public String getNombreCompleto(){
        return this.name != null && this.surnamefather != null && this.surnamemother != null ?
                this.name + " " + this.surnamefather + " " + this.surnamemother: "-----";
    }
}
