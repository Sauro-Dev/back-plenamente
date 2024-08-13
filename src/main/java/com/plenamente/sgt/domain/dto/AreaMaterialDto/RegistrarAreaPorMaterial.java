package com.plenamente.sgt.domain.dto.AreaMaterialDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarAreaPorMaterial(
        @NotNull Long idAreaIntervencion,  // ID del AreaIntervencion asociada
        @NotBlank String descripcionMaterial
) {
}