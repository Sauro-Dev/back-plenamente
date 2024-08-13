package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "areaMaterial")
@Getter
@Setter
@NoArgsConstructor
public class AreaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAreaMaterial;

    @ManyToOne
    @JoinColumn(name = "idAreaIntervencion", nullable = false)
    private AreaIntervencion areaIntervencion;
}