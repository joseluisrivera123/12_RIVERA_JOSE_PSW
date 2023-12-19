package com.example.as211bereport.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activities {

    private Integer id;
    private String name;
    private String description;
    private LocalDate date;
    private String duration;
    private String location;
    private String active;
    private String type_pronacej;
    private String type_soa;


    public Activities(String name, String description, LocalDate date, String duration, String location, String active, String type_pronacej, String type_soa) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.location = location;
        this.active = active;
        this.type_pronacej = type_pronacej;
        this.type_soa = type_soa;
    }

}
