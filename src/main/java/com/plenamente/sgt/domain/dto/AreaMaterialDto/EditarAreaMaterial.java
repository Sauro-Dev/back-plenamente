package com.plenamente.sgt.domain.dto.AreaMaterialDto;
import jakarta.validation.constraints.NotBlank;

public record EditarAreaMaterial(
        @NotBlank String descripcionMaterial
) {
}