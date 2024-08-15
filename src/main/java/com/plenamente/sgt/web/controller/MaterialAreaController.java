package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.MaterialAreaDto.CreateAreaForMaterial;
import com.plenamente.sgt.domain.dto.MaterialAreaDto.UpdateMaterialArea;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.service.MaterialAreaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/material-area")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MaterialAreaController {

    private final MaterialAreaService materialAreaService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<MaterialArea> createAreaForMaterial(@RequestBody @Valid CreateAreaForMaterial dto) {
        MaterialArea materialArea = materialAreaService.createAreaForMaterial(dto);
        return ResponseEntity.ok(materialArea);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MaterialArea> updateMaterialArea(@PathVariable Long id, @RequestBody @Valid UpdateMaterialArea dto) {
        MaterialArea materialArea = materialAreaService.updateMaterialArea(id, dto);
        return ResponseEntity.ok(materialArea);
    }
}
