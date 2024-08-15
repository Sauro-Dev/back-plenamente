package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "material_area")
@Getter
@Setter
@NoArgsConstructor
public class MaterialArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "intervention_area_id", nullable = false)
    private InterventionArea interventionArea;
    
}