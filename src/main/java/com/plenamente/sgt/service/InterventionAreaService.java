package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.InterventionArea;

public interface InterventionAreaService {
    InterventionArea createAreaForIntervention(String name, String description);
}