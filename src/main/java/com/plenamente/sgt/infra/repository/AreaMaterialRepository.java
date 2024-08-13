package com.plenamente.sgt.infra.repository;


import com.plenamente.sgt.domain.entity.AreaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMaterialRepository extends JpaRepository<AreaMaterial, Long> {
    // Puedes agregar métodos personalizados si es necesario
}