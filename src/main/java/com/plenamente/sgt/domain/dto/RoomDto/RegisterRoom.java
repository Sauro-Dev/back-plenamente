package com.plenamente.sgt.domain.dto.RoomDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterRoom(
        @NotNull @Size(min = 1, max = 100) String name,
        @NotNull @Length(max = 100) String address,
        @NotNull boolean isTherapeutic
) {
}
