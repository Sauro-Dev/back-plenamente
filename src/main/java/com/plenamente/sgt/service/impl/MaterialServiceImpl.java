package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialStatus;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Material registerMaterial(RegisterMaterial dto) {
        // Crear el material
        Material material = new Material();
        material.setIdMaterial(dto.idMaterial());  // Asegurarse de que venga desde el CSV
        material.setNombre(dto.nombre());
        material.setDescripcion(dto.descripcion());
        material.setStock(dto.stock());
        material.setEsCompleto(dto.esCompleto());
        material.setEsSoporte(dto.esSoporte());
        material.setEstado(dto.estado());
        // Guardar el material
        return materialRepository.save(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

}
