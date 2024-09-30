package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.MaterialDto.RegisterMaterial;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/register")
    public ResponseEntity<Material> registerMaterial(@RequestBody RegisterMaterial dto) {
        Material newMaterial = materialService.registerMaterial(dto);
        return new ResponseEntity<>(newMaterial, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/select/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable String id) {
        Material material = materialService.getMaterialById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Material> updateMaterial(
            @PathVariable String id,
            @RequestBody RegisterMaterial updatedMaterial) {
        Material updated = materialService.updateMaterial(id, updatedMaterial);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
