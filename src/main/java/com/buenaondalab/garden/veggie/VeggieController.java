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
    public Set<VeggieDTO> getVeggies(){
        log.info("Retrieving veggies list...");
        VeggieDTO sedano = new VeggieDTO();
        sedano.setId(1L);
        sedano.setName("sedano");
        VeggieDTO carota = new VeggieDTO();
        carota.setId(2L);
        carota.setName("carota");
        VeggieDTO cipolla = new VeggieDTO();
        cipolla.setId(3L);
        cipolla.setName("cipolla");

        return new HashSet<>(Arrays.asList(sedano, carota, cipolla));
    }
    
}
