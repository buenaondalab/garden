package com.buenaondalab.garden.veggie;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @EqualsAndHashCode
public class Veggie {

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Veggie> associations = new HashSet<>();

    public Veggie(String name) {
        this.name = name;
    }
    
    
}
