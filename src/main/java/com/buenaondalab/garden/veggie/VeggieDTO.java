package com.buenaondalab.garden.veggie;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor
public class VeggieDTO {

    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<VeggieDTO> associations = new HashSet<>();

    public VeggieDTO(String name) {
        this.name = name;
    }
    
    
}
