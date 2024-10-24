package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;

import java.time.LocalDateTime;

public record MaterialById(
        String name,
        Integer stock,
        String description,
        Boolean isComplete,
        boolean isSupport,
        MaterialStatus status,
        String room,
        String area,
        LocalDateTime highDate
) {
}
