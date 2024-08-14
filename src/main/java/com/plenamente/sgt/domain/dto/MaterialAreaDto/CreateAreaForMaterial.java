package com.plenamente.sgt.domain.dto.MaterialAreaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAreaForMaterial(
        @NotNull Long interventionAreaId,  // ID of the associated InterventionArea
        @NotBlank String materialDescription
) {
}