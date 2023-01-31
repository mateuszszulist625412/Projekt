package com.example.projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Shoe> shoes;

    public Customer(Integer id, String name, String surname, String email, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
