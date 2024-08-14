package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.service.InterventionAreaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/intervention-area")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InterventionAreaController {

    private final InterventionAreaService interventionAreaService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<InterventionArea> createAreaForIntervention(@RequestBody @Valid CreateAreaForIntervention dto) {
        InterventionArea interventionArea = interventionAreaService.createAreaForIntervention(dto);
        return ResponseEntity.ok(interventionArea);
    }
}
