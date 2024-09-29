package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.entity.Material;

import java.util.List;

public interface MaterialService {
    Material registerMaterial(RegisterMaterial dto);
    List<Material> getAllMaterials();
}