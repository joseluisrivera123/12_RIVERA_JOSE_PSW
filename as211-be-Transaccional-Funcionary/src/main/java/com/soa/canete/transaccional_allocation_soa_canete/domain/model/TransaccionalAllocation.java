package com.soa.canete.transaccional_allocation_soa_canete.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "funcionarios_adolescente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionalAllocation {

    @Id
    private Integer id_funcionaryteend;
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

    public TransaccionalAllocation(String description, String estado, Integer id_adolescente, Integer id_funcionary, LocalDate start_date, LocalDate register_date) {
        this.description = description;
        this.estado = estado;
        this.id_adolescente = id_adolescente;
        this.id_funcionary = id_funcionary;
        this.start_date = start_date;
        this.register_date = register_date;
    }

}
