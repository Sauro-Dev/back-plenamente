package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record UpdateMaterial(
        @NotNull @Size(min = 1, max = 100) String name,
        @Size(min = 10, max = 150) String description,
        @Min(0) Integer stock,
        Boolean isComplete,
        boolean isSupport,
        MaterialStatus status,
        @NotNull Long roomId,
        @NotNull List<Long> interventionAreaIds
) {
}
