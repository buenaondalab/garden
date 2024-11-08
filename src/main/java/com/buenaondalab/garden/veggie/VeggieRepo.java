package com.buenaondalab.garden.veggie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeggieRepo extends JpaRepository<Veggie, Long> {
    List<Veggie> findAllByOrderByName();
    Veggie findByNameIgnoreCase(String name);
    
}
