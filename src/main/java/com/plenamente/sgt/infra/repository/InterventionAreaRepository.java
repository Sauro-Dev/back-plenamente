package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.InterventionArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionAreaRepository extends JpaRepository<InterventionArea, Long> {
    // Custom methods can be added here if needed
}