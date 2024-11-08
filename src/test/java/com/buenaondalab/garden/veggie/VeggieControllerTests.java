package com.buenaondalab.garden.veggie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@WebMvcTest(VeggieController.class)
class VeggieControllerTests {

    @Autowired
	private MockMvc mvc;

    @MockBean
    private VeggieService service;

    @Test
    @DisplayName("get /veggies")
    void getVeggies() throws Exception {
        Set<VeggieDTO> veggies = new HashSet<>(Arrays.asList(new VeggieDTO("carota"), new VeggieDTO("cipolla")));
        when(service.getAllVeggies()).thenReturn(veggies);
        mvc.perform(get("/veggies").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$[0].name").value("carota"))
           .andExpect(jsonPath("$[1].name").value("cipolla"));
    }

    @Test
    @DisplayName("get /veggies - no veggies")
    void getNoVeggies() throws Exception {
        when(service.getAllVeggies()).thenReturn(new HashSet<>());
        mvc.perform(get("/veggies").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(content().json("[]"));
    }

    @ParameterizedTest
    @DisplayName("get /veggie")
    @ValueSource(strings = {"pomodoro", "cipolla"})
    void getVeggie(String name) throws Exception {
        when(service.getVeggie(name)).thenReturn(Optional.of(new VeggieDTO(name)));
        mvc.perform(get("/veggie?name={name}", name).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.name").value(name)
        );
    }

    @ParameterizedTest
    @DisplayName("get /veggie - no veggie")
    @ValueSource(strings = {"ortaggio inesistente"})
    void getNoVeggie(String name) throws Exception {
        when(service.getVeggie(name)).thenReturn(Optional.empty());
        mvc.perform(get("/veggie?name={name}", name).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(content().string("null"));
    }
    
}
