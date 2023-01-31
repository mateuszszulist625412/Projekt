package com.example.projekt.services;

import com.example.projekt.Dto.CompanyDto;
import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.objects.Company;
import com.example.projekt.objects.Shoe;
import com.example.projekt.repositories.CompanyRepository;
import com.example.projekt.repositories.ShoeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ShoeRepository shoeRepository;

    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtoList = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            companyDtoList.add(new CompanyDto(company.getId(), company.getName(), company.getEmail(), company.getPhoneNumber()));
        }
        return companyDtoList;
    }

    public void saveCompany(CompanyDto companyDto) {
        companyRepository.save(new Company(companyDto.getId(), companyDto.getName(), companyDto.getEmail(), companyDto.getPhoneNumber()));
    }

    public void updateCompany(CompanyDto companyDto) {
        var company = companyRepository.findById(companyDto.getId()).orElse(null);
        if (company != null) {
            company.setName(companyDto.getName());
            company.setEmail(companyDto.getEmail());
            company.setPhoneNumber(companyDto.getPhoneNumber());
            companyRepository.save(company);
        }
    }

    public void deleteCompany(int companyId) {
        companyRepository.findById(companyId).ifPresent(company -> companyRepository.delete(company));
    }

    public void addShoe(int companyId, ShoeDto shoeDto) {
        var company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            var shoe = new Shoe();
            shoe.setId(shoeDto.getId());
            shoe.setModel(shoeDto.getModel());
            shoe.setColour(shoeDto.getColour());
            shoe.setPrice(shoeDto.getPrice());
            shoe.setCompany(company);
            company.getShoes().add(shoe);
            shoeRepository.save(shoe);
            companyRepository.save(company);
        }
    }
    public List<ShoeDto> getShoes(int customerId) {
        List<ShoeDto> shoeDtoList = new ArrayList<>();
        var company = companyRepository.findById(customerId).orElse(null);
        if (company != null) {
            var shoes = company.getShoes();
            for (Shoe shoe : shoes) {
                shoeDtoList.add(new ShoeDto(shoe.getId(), shoe.getModel(), shoe.getColour(), shoe.getPrice(), shoe.getType()));
            }
        }
        return shoeDtoList;
    }
}
