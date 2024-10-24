package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.MaterialDto.AllMaterials;
import com.plenamente.sgt.domain.dto.MaterialDto.MaterialById;
import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.dto.MaterialDto.UpdateMaterial;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/materials")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/register")
    public ResponseEntity<Material> registerMaterial(@RequestBody RegisterMaterial dto) {

        Material material = new Material();
        material.setName(dto.name().replace("\"","'"));
        material.setDescription(dto.description());
        material.setStock(dto.stock());
        material.setIsComplete(dto.isComplete());
        material.setSupport(dto.isSupport());
        material.setStatus(dto.status());

        Material newMaterial = materialService.registerMaterial(material);
        return new ResponseEntity<>(newMaterial, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllMaterials>> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        List<AllMaterials> dtoList = materials.stream()
                .map(material -> new AllMaterials(
                        material.getIdMaterial(),
                        material.getName().replace("\"", "'"),
                        material.getStock(),
                        material.getIsComplete(),
                        material.isSupport(),
                        material.getStatus(),
                        material.getRoom() != null ? material.getRoom().getName() : "Sin area asignada",
                        material.getHighDate()
                ))
                .toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/select/{id}")
    public ResponseEntity<MaterialById> getMaterialById(@PathVariable String id) {
        Material material = materialService.getMaterialById(id);

        // Obtener nombre de las areas de intervencion
        String areas = material.getMaterialAreas() != null && !material.getMaterialAreas().isEmpty()
                ? material.getMaterialAreas().stream()
                    .map(ma -> ma.getInterventionArea().getName())// Obtener el nombre del area de intervención
                    .collect(Collectors.joining(", "))
                : "Sin áreas de intervención asignadas";

        MaterialById dtoMaterialById = new MaterialById(
                material.getName().replace("\"","'"),
                material.getStock(),
                material.getDescription(),
                material.getIsComplete(),
                material.isSupport(),
                material.getStatus(),
                material.getRoom() != null ? material.getRoom().getName() : "Sin sala asignada",
                areas,
                material.getHighDate()
        );

        return new ResponseEntity<>(dtoMaterialById, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Material> updateMaterial(
            @PathVariable String id,
            @RequestBody UpdateMaterial updatedMaterial) {

        Material material = new Material();
        material.setName(updatedMaterial.name().replace("\"","'"));
        material.setDescription(updatedMaterial.description());
        material.setStock(updatedMaterial.stock());
        material.setIsComplete(updatedMaterial.isComplete());
        material.setSupport(updatedMaterial.isSupport());
        material.setStatus(updatedMaterial.status());

        materialService.updateMaterial(id, material);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
