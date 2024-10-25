package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jdk.jfr.Enabled;

import java.util.List;

public record RegisterMaterial(
        @NotNull @Size(min = 1, max = 100) String name,
        String description,
        @Min(0) Integer stock,
        Boolean isComplete,
        @NotNull boolean isSupport,
        MaterialStatus status,
        Long roomId,
        List<Long> interventionAreaIds
) {
}