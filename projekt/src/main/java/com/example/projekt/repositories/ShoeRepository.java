package com.example.projekt.repositories;

import com.example.projekt.objects.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoeRepository extends JpaRepository<Shoe, Integer> {
    Optional<Shoe> findAllByType(String type);
}
