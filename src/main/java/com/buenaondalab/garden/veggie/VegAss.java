package com.buenaondalab.garden.veggie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "VEGASS")
@Data @RequiredArgsConstructor @NoArgsConstructor
public class VegAss {

    @Id @EqualsAndHashCode.Include
    @GeneratedValue
    @NonNull
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "VEGGIE1")
    private Veggie veggie1;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "VEGGIE2")
    private Veggie veggie2;
    
}
