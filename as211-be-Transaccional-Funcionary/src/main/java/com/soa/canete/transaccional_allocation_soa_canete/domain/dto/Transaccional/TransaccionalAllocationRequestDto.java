package com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransaccionalAllocationRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Column
    private String description;
    @Column
    private String estado;
    @Column("id_adolescente")
    private Integer id_adolescente;
    @Column("id_funcionary")
    private Integer id_funcionary;
    private LocalDate start_date;
    private LocalDate register_date;
}
