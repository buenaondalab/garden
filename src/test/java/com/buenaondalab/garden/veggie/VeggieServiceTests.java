package com.buenaondalab.garden.veggie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

@TestInstance(Lifecycle.PER_CLASS)
class VeggieServiceTests {

    @Mock
    private VegAssRepo assRepo;
    @Mock
    private VeggieRepo vegRepo;
    private VeggieService service;

    private Veggie carrot = new Veggie();
    private Veggie onion = new Veggie();        
    private Veggie celery = new Veggie();
    private Veggie tomato = new Veggie();
    private Veggie basil = new Veggie();        

    @BeforeAll
    void initTests() {
        initVeggies();
        MockitoAnnotations.openMocks(this);
        service = new VeggieService(assRepo, vegRepo, new ModelMapper());
    }

    @Test
    @DisplayName("Get veggie")
    void getVeggie() {
        when(assRepo.findByVeggie1OrVeggie2(celery, celery))
            .thenReturn(
                Arrays.asList(
                    new VegAss(2L, celery, onion)
                )
            );
        when(vegRepo.findByNameIgnoreCase("celery")).thenReturn(celery);

        VeggieDTO celeryDTO = service.getVeggie("celery").get();
        assertEquals(1, celeryDTO.getAssociations().size());
        assertEquals("onion", celeryDTO.getAssociations().toArray(new VeggieDTO[1])[0].getName());
    }

    @Test
    @DisplayName("Get the unique veggie association")
    void getUniqueAssociation() {
        
        when(assRepo.findByVeggie1OrVeggie2(tomato, tomato))
            .thenReturn(
                Arrays.asList(
                    new VegAss(1L, tomato, basil)
                )
            );
        VeggieDTO basilDTO = service.toDTO.apply(basil);

        Set<VeggieDTO> tomatoAss = service.getVegAss(tomato);
        assertEquals(1, tomatoAss.size());
        assertTrue(tomatoAss.contains(basilDTO));
    }

    @Test
    @DisplayName("Get many veggie associations")
    void getAssociations() {

        when(assRepo.findByVeggie1OrVeggie2(onion, onion))
            .thenReturn(
                Arrays.asList(
                    new VegAss(1L, onion, carrot),
                    new VegAss(2L, celery, onion)
                )
            );
        
        VeggieDTO carrotDTO = service.toDTO.apply(carrot);
        VeggieDTO celeryDTO = service.toDTO.apply(celery);

        Set<VeggieDTO> onionAss = service.getVegAss(onion);
        assertEquals(2, onionAss.size());
        assertTrue(onionAss.contains(celeryDTO));
        assertTrue(onionAss.contains(carrotDTO));
    }

    private void initVeggies() {
        tomato.setName("tomato");
        tomato.setId(1L);
        basil.setName("basil");
        basil.setId(2L);
        carrot.setName("carrot");
        carrot.setId(3L);
        onion.setName("onion");
        onion.setId(4L);
        celery.setName("celery");
        celery.setId(5L);
    }
}
