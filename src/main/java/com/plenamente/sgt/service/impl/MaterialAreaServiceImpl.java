package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialAreaRepository;
import com.plenamente.sgt.service.MaterialAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MaterialAreaServiceImpl implements MaterialAreaService {

    private final MaterialAreaRepository materialAreaRepository;
    private final InterventionAreaRepository interventionAreaRepository;

    @Override
    public MaterialArea createAreaForMaterial(String interventionAreaName) {
        InterventionArea interventionArea = interventionAreaRepository.findByName(interventionAreaName)
                .orElseThrow(() -> new ResourceNotFoundException("Área de intervención no encontrada con nombre: " + interventionAreaName));

        MaterialArea materialArea = new MaterialArea();
        materialArea.setInterventionArea(interventionArea);

        return materialAreaRepository.save(materialArea);
    }

    @Override
    public MaterialArea updateMaterialArea(Long id) {
        MaterialArea materialArea = materialAreaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área de material no encontrada con id: " + id));

        return materialAreaRepository.save(materialArea);
    }
}