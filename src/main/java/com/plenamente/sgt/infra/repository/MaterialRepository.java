package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, String> {
    Optional<Material> findTopByOrderByIdMaterialDesc();
    List<Material> findByRoomIsNull();
    List<Material> findByRoom(Room room);
}