package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Material registerMaterial(RegisterMaterial dto) {
        Material material = new Material();
        material.setNombre(dto.nombre());
        material.setDescripcion(dto.descripcion());
        material.setStock(dto.stock());
        material.setEsCompleto(dto.esCompleto());
        material.setEsSoporte(dto.esSoporte());
        material.setEstado(dto.estado());
        return materialRepository.save(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterialById(String id) {
        return materialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + id));
    }

    @Override
    public Material updateMaterial(String id, RegisterMaterial updatedMaterial) {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + id));

        existingMaterial.setNombre(updatedMaterial.nombre());
        existingMaterial.setDescripcion(updatedMaterial.descripcion());
        existingMaterial.setStock(updatedMaterial.stock());
        existingMaterial.setEsCompleto(updatedMaterial.esCompleto());
        existingMaterial.setEsSoporte(updatedMaterial.esSoporte());
        existingMaterial.setEstado(updatedMaterial.estado());

        return materialRepository.save(existingMaterial);
    }

    @Override
    public String generateNextMaterialId() {
        // Obtener el último ID insertado (puedes ordenar por el campo transactionId)
        Optional<Material> lastMaterialOpt = materialRepository.findTopByOrderByIdMaterialDesc();

        String lastMaterialId = lastMaterialOpt.map(Material::getIdMaterial).orElse("A000");

        // Extraer la parte alfabética y la parte numérica
        String alphaPart = lastMaterialId.substring(0, 1);  // Letra
        int numericPart = Integer.parseInt(lastMaterialId.substring(1));  // Números

        // Incrementar la parte numérica
        numericPart++;

        // Si la parte numérica llega a 1000, reiniciarla y pasar a la siguiente letra
        if (numericPart > 999) {
            numericPart = 1;  // Reiniciar la parte numérica
            // Incrementar la parte alfabética (A-Z)
            alphaPart = incrementAlphaPart(alphaPart);
        }

        // Formatear el nuevo ID (A001, B001, etc.)
        return String.format("%s%03d", alphaPart, numericPart);
    }

    @Override
    public String incrementAlphaPart(String alphaPart) {
        // Convertir la letra a su valor ASCII y sumar 1
        char nextChar = (char) (alphaPart.charAt(0) + 1);

        // Si se pasa de 'Z', no permite continuar (puedes cambiar la lógica si es necesario)
        if (nextChar > 'Z') {
            throw new IllegalStateException("Se ha alcanzado el límite máximo de IDs: Z999");
        }

        return String.valueOf(nextChar);
    }
}