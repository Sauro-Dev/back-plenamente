package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.AllInterventionArea;
import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.service.InterventionAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervention-areas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InterventionAreaController {

    private final InterventionAreaService interventionAreaService;

    @PostMapping("/register")
    public ResponseEntity<InterventionArea> createInterventionArea(@RequestBody @Valid CreateAreaForIntervention dto) {
        InterventionArea interventionArea = interventionAreaService.createAreaForIntervention(dto.name(), dto.description());
        return new ResponseEntity<>(interventionArea, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllInterventionArea>> getAllInterventionAreas() {
        List<InterventionArea> areas = interventionAreaService.getAllInterventionAreas();
        List<AllInterventionArea> dtoList = areas.stream()
                .map(interventionArea -> new AllInterventionArea(
                        interventionArea.getName(),
                        interventionArea.getDescription()
                ))
                .toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}