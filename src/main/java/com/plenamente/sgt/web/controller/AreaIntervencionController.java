package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.AreaIntervencionDto.RegistrarAreaPorIntervencion;
import com.plenamente.sgt.domain.entity.AreaIntervencion;
import com.plenamente.sgt.service.AreaIntervencionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/areaIntervencion")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AreaIntervencionController {

    private final AreaIntervencionService areaIntervencionService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<AreaIntervencion> registrarAreaPorIntervencion(@RequestBody @Valid RegistrarAreaPorIntervencion dto) {
        AreaIntervencion areaIntervencion = areaIntervencionService.registrarAreaPorIntervencion(dto);
        return ResponseEntity.ok(areaIntervencion);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AreaIntervencion> actualizarAreaIntervencion(@PathVariable Long id, @RequestBody @Valid RegistrarAreaPorIntervencion dto) {
        AreaIntervencion areaIntervencion = areaIntervencionService.actualizarAreaIntervencion(id, dto);
        return ResponseEntity.ok(areaIntervencion);
    }
}
