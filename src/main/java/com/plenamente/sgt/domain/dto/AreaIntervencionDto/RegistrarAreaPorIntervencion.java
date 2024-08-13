package com.plenamente.sgt.domain.dto.AreaIntervencionDto;

import jakarta.validation.constraints.NotBlank;

public record RegistrarAreaPorIntervencion(
        @NotBlank String nombre,
        @NotBlank String descripcion
) {
}