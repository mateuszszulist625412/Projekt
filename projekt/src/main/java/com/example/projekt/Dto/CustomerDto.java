package com.example.projekt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    @JsonProperty("phone_number")
    private Integer phoneNumber;
}
