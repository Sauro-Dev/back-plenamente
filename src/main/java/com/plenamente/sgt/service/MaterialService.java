package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.entity.Material;

import java.util.List;

public interface MaterialService {
    Material registerMaterial(Material material, Long roomId, List<Long> interventionAreaIds);
    List<Material> getAllMaterials();
    Material getMaterialById(String id);
    Material updateMaterial(String id, Material material);
    String generateNextMaterialId();
    String incrementAlphaPart(String alphaPart);

    Material assignMaterialToRoom(String materialId, Long roomId);

    Material unassignMaterialFromRoom(String materialId);

    List<Material> getUnassignedMaterials();

}