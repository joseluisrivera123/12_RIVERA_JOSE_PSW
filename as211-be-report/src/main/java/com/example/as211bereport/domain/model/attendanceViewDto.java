package com.example.as211bereport.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class attendanceViewDto {

    private static final long serialVersionUID = 8222253670338491507L;
    private Integer id;
    private Integer idactiviti;
    private Integer idadolescente;
    private String name;
    private String asistencia;
    private String active;
    private LocalDate date;
}
