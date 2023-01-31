package com.example.projekt.repositories;

import com.example.projekt.objects.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
