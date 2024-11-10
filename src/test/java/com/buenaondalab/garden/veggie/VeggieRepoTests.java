package com.buenaondalab.garden.veggie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(showSql = true)
@ActiveProfiles(value = "test")
class VeggieRepoTests {

    @Autowired
    private VeggieRepo repo;

    @ParameterizedTest
    @ValueSource(strings = {"POMODORO", "pomodoro", "Pomodoro", "PomOdOrO"})
    @DisplayName("")
    void findByName(String name) {
        Veggie tomato = repo.findByNameIgnoreCase(name);
        assertEquals("pomodoro", tomato.getName());
    }

    @Test
    @DisplayName("findByNameIgnoreCase - no results")
    void findByNameNoResults() {
        Veggie result = repo.findByNameIgnoreCase("Pommidoro");
        assertNull(result);

    }
    
}
