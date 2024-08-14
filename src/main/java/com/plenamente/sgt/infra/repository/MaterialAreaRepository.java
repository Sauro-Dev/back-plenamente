package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.MaterialArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialAreaRepository extends JpaRepository<MaterialArea, Long> {
    // You can add custom methods if necessary
}
