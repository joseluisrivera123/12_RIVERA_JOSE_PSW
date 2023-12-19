package com.example.as211bereport.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Address {

    private String address;
    private String active;
    public Address(String address,  String active) {
        this.address = address;
        this.active = active;
    }
}
