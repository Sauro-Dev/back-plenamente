package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.AreaMaterialDto.EditarAreaMaterial;
import com.plenamente.sgt.domain.dto.AreaMaterialDto.RegistrarAreaPorMaterial;
import com.plenamente.sgt.domain.entity.AreaMaterial;

public interface AreaMaterialService {
    AreaMaterial registrarAreaPorMaterial(RegistrarAreaPorMaterial dto);
    AreaMaterial editarArea(Long id, EditarAreaMaterial dto);
}
