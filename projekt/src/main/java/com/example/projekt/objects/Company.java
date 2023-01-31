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
public class Company {
    @Id
    private Integer id;
    private String name;
    private String email;
    private Integer phoneNumber;

    @OneToMany(mappedBy = "company")
    private List<Shoe> shoes;

    public Company(Integer id, String name, String email, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}