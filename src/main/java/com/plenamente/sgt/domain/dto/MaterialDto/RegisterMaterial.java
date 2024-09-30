package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;

public record RegisterMaterial(
        String idMaterial,  // String para el ID del material
        String nombre,
        String descripcion,
        int stock,
        boolean esCompleto,
        boolean esSoporte,
        MaterialStatus estado
) {
}