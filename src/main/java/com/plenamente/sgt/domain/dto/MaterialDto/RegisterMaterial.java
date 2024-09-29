package com.plenamente.sgt.domain.dto.MaterialDto;

public record RegisterMaterial(
        String idMaterial,  // String para el ID del material
        String nombre,
        String descripcion,
        int stock,
        boolean esCompleto,
        boolean esSoporte
) {
}