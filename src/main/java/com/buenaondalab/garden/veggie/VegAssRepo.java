package com.buenaondalab.garden.veggie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VegAssRepo extends JpaRepository<VegAss, Long> {
    List<VegAss> findByVeggie1OrVeggie2(Veggie v1, Veggie v2);
}
