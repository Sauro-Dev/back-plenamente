package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.MaterialArea;

public interface MaterialAreaService {
    MaterialArea createAreaForMaterial(String interventionAreaName);
    MaterialArea updateMaterialArea(Long id);
}