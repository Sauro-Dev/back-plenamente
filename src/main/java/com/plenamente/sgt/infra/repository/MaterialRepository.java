package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
    Optional<Material> findTopByOrderByIdMaterialDesc();
    // Métodos de consulta adicionales según sea necesario
}