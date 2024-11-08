package com.buenaondalab.garden.veggie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController @Log @RequiredArgsConstructor
public class VeggieController {

    @Autowired
    VeggieService service;

    @GetMapping("/veggies")
    public Set<VeggieDTO> getVeggies(){
        log.info("Retrieving veggies list...");
        return service.getAllVeggies();
    }

    @GetMapping("/veggie")
    public Optional<VeggieDTO> getVeggie(@RequestParam String name) {
        log.info("Retrieving " + name + " data...");
        return service.getVeggie(name); 
    }   
    
}
