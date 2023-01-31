package com.example.projekt.controllers;

import com.example.projekt.Dto.CustomerDto;
import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping (value = "customer", method = RequestMethod.GET)
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @RequestMapping (value = "customer", method = RequestMethod.POST)
    public void saveCustomer(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }

    @RequestMapping (value = "customer/{id}/delete", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Integer id) {
        if (customerService.getCustomerRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "customer not found");
        }else {
            customerService.deleteCustomer(id);
        }
    }

    @RequestMapping (value = "customer/{id}/update", method = RequestMethod.PUT)
    public void updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        if (customerService.getCustomerRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "cerson not found");
        }else {
            customerDto.setId(id);
            customerService.updateCustomer(customerDto);
        }
    }

    @RequestMapping (value = "customer/{id}/shoe", method = RequestMethod.GET)
    public List<ShoeDto> getShoe(@PathVariable Integer id) {
        var shoe = customerService.getShoes(id);
        if(shoe.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            return shoe;
        }
    }

    @RequestMapping (value = "customer/{id}/shoe", method = RequestMethod.POST)
    public void addShoe(@PathVariable Integer id, @RequestBody ShoeDto shoeDto) {
        if(customerService.getCustomerRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            customerService.addShoe(id, shoeDto);
        }
    }
}
