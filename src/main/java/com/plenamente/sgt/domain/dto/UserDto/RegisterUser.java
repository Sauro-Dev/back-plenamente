package com.plenamente.sgt.domain.dto.UserDto;

import com.plenamente.sgt.domain.entity.Rol;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record RegisterUser(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 100) String paternalSurname,
        @Length(max = 100) String maternalSurname,
        @NotNull @Size(max = 9) String phone,
        @Size(max = 9) String phoneBackup,
        @NotNull @Pattern(regexp = "\\d{8}") String dni,
        @NotBlank @Email @Length(max = 100) String email,
        @Length(max = 100) String address,
        @Lob String photo,
        @NotBlank @Length(max = 100) String username,
        @NotBlank @Length(max = 100) String password,
        @NotNull Rol role,  // campo para determinar el rol
        Double pasoSesion,  // campo opcional para Therapist
        Double pagoMensual  // campo opcional para Secretary
) {

}
