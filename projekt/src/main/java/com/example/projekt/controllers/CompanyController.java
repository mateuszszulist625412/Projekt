package com.example.projekt.controllers;

import com.example.projekt.Dto.CompanyDto;
import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "company", method = RequestMethod.GET)
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @RequestMapping (value = "company", method = RequestMethod.POST)
    public void save(@RequestBody CompanyDto companyDto) {
        companyService.saveCompany(companyDto);
    }

    @RequestMapping (value = "company/{id}/delete", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable Integer id) {
        if (companyService.getCompanyRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "company not found");
        }else {
            companyService.deleteCompany(id);
        }
    }

    @RequestMapping (value = "company/{id}/update", method = RequestMethod.PUT)
    public void updateCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        if (companyService.getCompanyRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "company not found");
        }else {
            companyDto.setId(id);
            companyService.updateCompany(companyDto);
        }
    }

    @RequestMapping (value = "company/{id}/shoe", method = RequestMethod.GET)
    public List<ShoeDto> getShoe(@PathVariable Integer id) {
        var shoe = companyService.getShoes(id);
        if(shoe.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            return shoe;
        }
    }

    @RequestMapping (value = "company/{id}/shoe", method = RequestMethod.POST)
    public void addShoe(@PathVariable Integer id, @RequestBody ShoeDto shoeDto) {
        if(companyService.getCompanyRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            companyService.addShoe(id, shoeDto);
        }
    }
}
