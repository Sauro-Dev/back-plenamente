package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.MaterialAreaDto.CreateAreaForMaterial;
import com.plenamente.sgt.domain.dto.MaterialAreaDto.UpdateMaterialArea;
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
    public MaterialArea createAreaForMaterial(CreateAreaForMaterial dto) {
        InterventionArea interventionArea = interventionAreaRepository.findById(dto.interventionAreaId())
                .orElseThrow(() -> new ResourceNotFoundException("Intervention Area not found with id: " + dto.interventionAreaId()));

        MaterialArea materialArea = new MaterialArea();
        materialArea.setInterventionArea(interventionArea);
        materialArea.setMaterialDescription(dto.materialDescription());

        return materialAreaRepository.save(materialArea);
    }

    @Override
    public MaterialArea updateMaterialArea(Long id, UpdateMaterialArea dto) {
        MaterialArea materialArea = materialAreaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material Area not found with id: " + id));

        materialArea.setMaterialDescription(dto.materialDescription());

        return materialAreaRepository.save(materialArea);
    }
}
