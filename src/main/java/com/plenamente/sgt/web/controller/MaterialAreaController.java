package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.service.MaterialAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/material-areas")
@CrossOrigin("")
@RequiredArgsConstructor
public class MaterialAreaController {

    private final MaterialAreaService materialAreaService;

    @PostMapping("/register")
    public ResponseEntity<MaterialArea> createMaterialArea(@RequestBody String interventionAreaName) {
        MaterialArea materialArea = materialAreaService.createAreaForMaterial(interventionAreaName);
        return new ResponseEntity<>(materialArea, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialArea> updateMaterialArea(@PathVariable Long id) {
        MaterialArea updatedMaterialArea = materialAreaService.updateMaterialArea(id);
        return ResponseEntity.ok(updatedMaterialArea);
    }
}