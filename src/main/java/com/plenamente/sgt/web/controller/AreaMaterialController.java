package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.AreaMaterialDto.EditarAreaMaterial;
import com.plenamente.sgt.domain.dto.AreaMaterialDto.RegistrarAreaPorMaterial;
import com.plenamente.sgt.domain.entity.AreaMaterial;
import com.plenamente.sgt.service.AreaMaterialService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/areaMaterial")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AreaMaterialController {

    private final AreaMaterialService areaMaterialService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<AreaMaterial> registrarAreaPorMaterial(@RequestBody @Valid RegistrarAreaPorMaterial dto) {
        AreaMaterial areaMaterial = areaMaterialService.registrarAreaPorMaterial(dto);
        return ResponseEntity.ok(areaMaterial);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AreaMaterial> editarAreaMaterial(@PathVariable Long id, @RequestBody @Valid EditarAreaMaterial dto) {
        AreaMaterial areaMaterial = areaMaterialService.editarArea(id, dto);
        return ResponseEntity.ok(areaMaterial);
    }
}