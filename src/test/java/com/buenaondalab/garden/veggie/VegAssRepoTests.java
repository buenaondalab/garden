package com.buenaondalab.garden.veggie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(showSql = true)
@ActiveProfiles(value = "test")
class VegAssRepoTests {

    @Autowired
    private VegAssRepo repo;
    
    @Test
    @DisplayName("Veggie associations repository - findByVeggie1OrVeggie2")
    void findByVeggie1OrVeggie2() {
        Veggie tomato = new Veggie();
        tomato.setName("pomodoro");
        tomato.setId(4L);
        List<VegAss> ass = repo.findByVeggie1OrVeggie2(tomato, tomato);
        assertEquals(1, ass.size());
        assertEquals("pomodoro", ass.get(0).getVeggie1().getName());

        Veggie onion = new Veggie();
        onion.setName("cipolla");
        onion.setId(3L);
        List<VegAss> ass2 = repo.findByVeggie1OrVeggie2(onion, onion);
        assertEquals(2, ass2.size());
        assertEquals("cipolla", ass2.get(0).getVeggie1().getName());
        assertEquals("cipolla", ass2.get(1).getVeggie1().getName());
    }
}
