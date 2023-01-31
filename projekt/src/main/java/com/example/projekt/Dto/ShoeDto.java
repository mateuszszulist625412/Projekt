package com.example.projekt.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoeDto {
    private Integer id;
    private String model;
    private String colour;
    private Double price;
    private String type;
}
