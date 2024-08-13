package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "areaIntervencion")
@Getter
@Setter
@NoArgsConstructor
public class AreaIntervencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAreaIntervencion;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "areaIntervencion")
    private List<AreaMaterial> areaMaterials;
}