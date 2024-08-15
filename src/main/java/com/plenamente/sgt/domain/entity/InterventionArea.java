package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "intervention_area")
@Getter
@Setter
@NoArgsConstructor
public class InterventionArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "interventionArea")
    private List<MaterialArea> materialAreas;
}