package com.example.projekt.services;

import com.example.projekt.Dto.CustomerDto;
import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.objects.Customer;
import com.example.projekt.objects.Shoe;
import com.example.projekt.repositories.CustomerRepository;
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
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShoeRepository shoeRepository;

    public List<CustomerDto> getAll() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customerDtoList.add(new CustomerDto(customer.getId(), customer.getName(), customer.getSurname(), customer.getEmail(), customer.getPhoneNumber()));
        }
        return customerDtoList;
    }

    public void saveCustomer(CustomerDto customerDto) {
        customerRepository.save(new Customer(customerDto.getId(), customerDto.getName(), customerDto.getSurname(), customerDto.getEmail(), customerDto.getPhoneNumber()));
    }

    public void deleteCustomer(int customerId) {
        customerRepository.findById(customerId).ifPresent(customer -> customerRepository.deleteById(customerId));
    }

    public void updateCustomer(CustomerDto customerDto) {
        var customer = customerRepository.findById(customerDto.getId()).orElse(null);
        if (customer != null) {
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customerRepository.save(customer);
        }
    }

    public void addShoe(int customerId, ShoeDto shoeDto) {
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            var shoe = new Shoe();
            shoe.setId(shoeDto.getId());
            shoe.setModel(shoeDto.getModel());
            shoe.setColour(shoeDto.getColour());
            shoe.setPrice(shoeDto.getPrice());
            shoe.setType(shoeDto.getType());
            shoe.setCustomer(customer);
            customer.getShoes().add(shoe);
            shoeRepository.save(shoe);
            customerRepository.save(customer);
        }
    }
    public List<ShoeDto> getShoes(int customerId) {
        List<ShoeDto> shoeDtoList = new ArrayList<>();
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            var shoes = customer.getShoes();
            for (Shoe shoe : shoes) {
                shoeDtoList.add(new ShoeDto(shoe.getId(), shoe.getModel(), shoe.getColour(), shoe.getPrice(), shoe.getType()));
            }
        }
        return shoeDtoList;
    }
}
