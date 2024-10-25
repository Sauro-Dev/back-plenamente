package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.infra.repository.RoomRepository;
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
    private final RoomRepository roomRepository;
    private final InterventionAreaRepository interventionAreaRepository;

    @Override
    public Material registerMaterial(Material material, Long roomId, List<Long> interventionAreaIds) {
        // Asignar la sala al material
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrado con id: " + roomId));
        material.setRoom(room);

        // Asignar las areas de intervencion al material
        List<MaterialArea> materialAreas = interventionAreaIds.stream()
                .map(interventionAreaId -> {
                    InterventionArea interventionArea = interventionAreaRepository.findById(interventionAreaId)
                            .orElseThrow(() -> new EntityNotFoundException("Área de intervención no encontrada con id: " + interventionAreaId));

                    MaterialArea materialArea = new MaterialArea();
                    materialArea.setMaterial(material);
                    materialArea.setInterventionArea(interventionArea);
                    return materialArea;
                }).toList();

        // Establecer la lista de MaterialArea en el material
        material.setMaterialAreas(materialAreas);

        // Generar el ID para el nuevo material
        String generatedId = generateNextMaterialId();  // Generar el ID incremental
        material.setIdMaterial(generatedId);  // Asignar el ID al material

        return materialRepository.save(material);  // Guardar el material en la base de datos
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterialById(String id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + id));
    }

    @Override
    public Material updateMaterial(String id, Material material) {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + id));

        existingMaterial.setName(material.getName());
        existingMaterial.setDescription(material.getDescription());
        existingMaterial.setStock(material.getStock());
        existingMaterial.setIsComplete(material.getIsComplete());
        existingMaterial.setSupport(material.isSupport());
        existingMaterial.setStatus(material.getStatus());

        return materialRepository.save(existingMaterial);
    }

    @Override
    public String generateNextMaterialId() {
        // Obtener el último ID insertado
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
            alphaPart = incrementAlphaPart(alphaPart);  // Incrementar la letra
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

    @Override
    public Material assignMaterialToRoom(String materialId, Long roomId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + materialId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room no encontrado con id: " + roomId));

        material.setRoom(room);
        return materialRepository.save(material);
    }

    @Override
    public Material unassignMaterialFromRoom(String materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + materialId));

        material.setRoom(null);  // Desasignamos el material de cualquier sala
        return materialRepository.save(material);
    }

    @Override
    public List<Material> getUnassignedMaterials() {
        return materialRepository.findByRoomIsNull();
    }

}
