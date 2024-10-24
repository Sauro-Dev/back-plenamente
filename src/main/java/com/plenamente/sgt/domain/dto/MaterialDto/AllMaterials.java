package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;

import java.time.LocalDateTime;

public record AllMaterials(
        String idMaterial,
        String name,
        Integer stock,
        Boolean isComplete,
        boolean isSupport,
        MaterialStatus status,
        String room,
        LocalDateTime highDate
) {
}
