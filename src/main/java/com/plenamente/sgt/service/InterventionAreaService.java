package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.InterventionArea;

import java.util.List;

public interface InterventionAreaService {
    InterventionArea createAreaForIntervention(String name, String description);

    List<InterventionArea> getAllInterventionAreas();
}