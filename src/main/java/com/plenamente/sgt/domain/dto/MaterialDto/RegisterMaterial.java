package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jdk.jfr.Enabled;

public record RegisterMaterial(
        @NotNull @Size(min = 1, max = 100) String name,
        String description,
        @Min(0) Integer stock,
        Boolean isComplete,
        @NotNull boolean isSupport,
        MaterialStatus status,
        String room,
        String area
) {
}