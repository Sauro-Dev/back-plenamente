package com.plenamente.sgt.domain.dto.InterventionAreaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAreaForIntervention(
        @NotNull @Size(min = 1, max = 100) String name,
        @NotBlank String description
) {
}