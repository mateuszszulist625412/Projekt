package com.example.projekt.objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shoe {
    @Id
    private Integer id;
    private String model;
    private String colour;
    private Double price;
    private String type;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Company company;

    public Shoe(Integer id, String model, String colour, Double price, String type) {
        this.id = id;
        this.model = model;
        this.colour = colour;
        this.price = price;
        this.type = type;
    }
}
