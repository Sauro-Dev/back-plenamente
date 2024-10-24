package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MaterialArea")
@Table(name = "material_areas")
@Getter
@Setter
@NoArgsConstructor
public class MaterialArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterialArea;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intervention_area_id")
    private InterventionArea interventionArea;

}