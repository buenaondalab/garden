package com.buenaondalab.garden.veggie;

import static java.util.function.Predicate.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VeggieService {

    private final VegAssRepo assRepo;
    private final VeggieRepo vegRepo;
    protected final Function<Veggie, VeggieDTO> toDTO;    

    public VeggieService(VegAssRepo assRepo, VeggieRepo vegRepo, ModelMapper mapper) {
        this.assRepo = assRepo;
        this.vegRepo = vegRepo;
        toDTO = v -> mapper.map(v, VeggieDTO.class);
    }

    public Set<VeggieDTO> getVegAss(Veggie veg){
        List<VegAss> associations = assRepo.findByVeggie1OrVeggie2(veg, veg);
        return associations.stream()
            .flatMap(ass -> Stream.of(ass.getVeggie1(), ass.getVeggie2()))
            .filter(not(isEqual(veg)))
            .distinct()
            .map(toDTO)
            .collect(Collectors.toSet());
    }

    public Optional<VeggieDTO> getVeggie(String name) {
        Optional<Veggie> veggie = Optional.ofNullable(vegRepo.findByNameIgnoreCase(name));
        return veggie.map(this::buildDTO);
    }

    public Set<VeggieDTO> getAllVeggies() {
        return vegRepo.findAllByOrderByName().stream()
            .map(toDTO)
            .collect(Collectors.toSet());
    }

    private VeggieDTO buildDTO(Veggie veggie) {
        Set<VeggieDTO> ass = getVegAss(veggie);
        VeggieDTO dto = toDTO.apply(veggie);
        dto.setAssociations(ass);
        return dto;
    }
}
