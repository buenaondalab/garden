package com.buenaondalab.garden.veggie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController @Log
public class VeggieController {

    @GetMapping("/veggies")
    public Set<Veggie> getVeggies(){
        log.info("Retrieving veggies list...");
        Veggie sedano = new Veggie("sedano");
        Veggie carota = new Veggie("carota");
        Veggie cipolla = new Veggie("cipolla");

        return new HashSet<>(Arrays.asList(sedano, carota, cipolla));
    }
    
}
