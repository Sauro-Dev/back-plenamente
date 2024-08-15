package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.MaterialAreaDto.CreateAreaForMaterial;
import com.plenamente.sgt.domain.dto.MaterialAreaDto.UpdateMaterialArea;
import com.plenamente.sgt.domain.entity.MaterialArea;

public interface MaterialAreaService {
    MaterialArea createAreaForMaterial(CreateAreaForMaterial dto);
    MaterialArea updateMaterialArea(Long id, UpdateMaterialArea dto);
}
