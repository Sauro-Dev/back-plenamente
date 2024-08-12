package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.*;

public class UserFactory {
    public static User createUser(Rol role) {
        return switch (role) {
            case THERAPIST -> new Therapist();
            case SECRETARY -> new Secretary();
            case ADMIN -> new Admin();
            default -> throw new IllegalArgumentException("Rol no soportado: " + role);
        };
    }
}
