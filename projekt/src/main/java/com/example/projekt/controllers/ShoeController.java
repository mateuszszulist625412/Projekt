package com.example.projekt.controllers;

import com.example.projekt.Dto.ShoeDto;
import com.example.projekt.services.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ShoeController {
    @Autowired
    private ShoeService shoeService;

    @RequestMapping(value = "shoe", method = RequestMethod.GET)
    public List<ShoeDto> getAll() {
        return shoeService.getAll();
    }

    @RequestMapping (value = "shoe/{type}", method = RequestMethod.GET)
    public List<ShoeDto> getShoesByType(@PathVariable String type) {
        if (shoeService.getShoeRepository().findAllByType(type).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoes not found");
        }
        return  shoeService.findAllByType(type);
    }

    @RequestMapping (value = "shoe", method = RequestMethod.POST)
    public void saveShoe(@RequestBody ShoeDto shoeDto) {
        shoeService.saveShoe(shoeDto);
    }

    @RequestMapping (value = "shoe/{id}/update", method = RequestMethod.PUT)
    public void updateShoe(@PathVariable Integer id, @RequestBody ShoeDto shoeDto) {
        if (shoeService.getShoeRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            shoeDto.setId(id);
            shoeService.updateShoe(shoeDto);
        }
    }

    @RequestMapping (value = "shoe/{id}/delete", method = RequestMethod.DELETE)
    public void deleteShoe(@PathVariable Integer id) {
        if (shoeService.getShoeRepository().findById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "shoe not found");
        }else {
            shoeService.deleteShoe(id);
        }
    }
}
