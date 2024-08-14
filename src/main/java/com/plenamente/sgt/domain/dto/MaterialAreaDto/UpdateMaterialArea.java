package com.plenamente.sgt.domain.dto.MaterialAreaDto;

import jakarta.validation.constraints.NotBlank;

public record UpdateMaterialArea(
        @NotBlank String materialDescription
) {
}