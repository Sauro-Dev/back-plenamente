package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.AreaIntervencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaIntervencionRepository extends JpaRepository<AreaIntervencion, Long> {
    // Puedes agregar métodos personalizados si es necesario
}