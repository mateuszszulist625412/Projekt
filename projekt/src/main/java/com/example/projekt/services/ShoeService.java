package com.example.projekt.services;

import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.objects.Shoe;
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
public class ShoeService {
    @Autowired
    private ShoeRepository shoeRepository;

    public List<ShoeDto> getAll() {
        List<ShoeDto> shoeDtoList = new ArrayList<>();
        for (Shoe shoe : shoeRepository.findAll()) {
            shoeDtoList.add(new ShoeDto(shoe.getId(), shoe.getModel(), shoe.getColour(), shoe.getPrice(), shoe.getType()));
        }
        return shoeDtoList;
    }

    public void saveShoe(ShoeDto shoeDto) {
        shoeRepository.save(new Shoe(shoeDto.getId(), shoeDto.getModel(), shoeDto.getColour(), shoeDto.getPrice(), shoeDto.getType()));
    }

    public List<ShoeDto> findAllByType(String type) {
        List<ShoeDto> shoeDtoList = new ArrayList<>();
        for (Shoe shoe : shoeRepository.findAll()) {
            if (shoe.getType().equals(type)) {
                shoeDtoList.add(new ShoeDto(shoe.getId(), shoe.getModel(), shoe.getColour(), shoe.getPrice(), shoe.getType()));
            }
        }
        return shoeDtoList;
    }

    public void updateShoe(ShoeDto shoeDto) {
        var shoe = shoeRepository.findById(shoeDto.getId()).orElse(null);
        if (shoe != null) {
            shoe.setModel(shoeDto.getModel());
            shoe.setColour(shoeDto.getColour());
            shoe.setPrice(shoeDto.getPrice());
            shoe.setType(shoeDto.getType());
            shoeRepository.save(shoe);
        }
    }

    public void deleteShoe(int shoeId) {
        shoeRepository.findById(shoeId).ifPresent(shoe -> shoeRepository.delete(shoe));
    }
}
