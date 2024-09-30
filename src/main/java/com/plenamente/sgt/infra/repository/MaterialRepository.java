package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
    // Métodos de consulta adicionales según sea necesario
}