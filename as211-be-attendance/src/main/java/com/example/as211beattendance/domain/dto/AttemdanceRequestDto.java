package com.example.as211beattendance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class AttemdanceRequestDto {
    private static final long serialVersionUID = 8222253670338491507L;
    private Integer idactiviti;
    private Integer idadolescente;
    private String asistencia;
    private String active;
    private LocalDate date;
}

