package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.AreaMaterialDto.EditarAreaMaterial;
import com.plenamente.sgt.domain.dto.AreaMaterialDto.RegistrarAreaPorMaterial;
import com.plenamente.sgt.domain.entity.AreaIntervencion;
import com.plenamente.sgt.domain.entity.AreaMaterial;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.AreaIntervencionRepository;
import com.plenamente.sgt.infra.repository.AreaMaterialRepository;
import com.plenamente.sgt.service.AreaMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AreaMaterialServiceImpl implements AreaMaterialService {

    private final AreaMaterialRepository areaMaterialRepository;
    private final AreaIntervencionRepository areaIntervencionRepository;

    @Override
    public AreaMaterial registrarAreaPorMaterial(RegistrarAreaPorMaterial dto) {
        AreaIntervencion areaIntervencion = areaIntervencionRepository.findById(dto.idAreaIntervencion())
                .orElseThrow(() -> new ResourceNotFoundException("Área de Intervención no encontrada con id: " + dto.idAreaIntervencion()));

        AreaMaterial areaMaterial = new AreaMaterial();
        areaMaterial.setAreaIntervencion(areaIntervencion);

        return areaMaterialRepository.save(areaMaterial);
    }

    @Override
    public AreaMaterial editarArea(Long id, EditarAreaMaterial dto) {
        AreaMaterial areaMaterial = areaMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área Material no encontrada con id: " + id));

        return areaMaterialRepository.save(areaMaterial);
    }
}
