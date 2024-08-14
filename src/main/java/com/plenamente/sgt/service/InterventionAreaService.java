package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;

public interface InterventionAreaService {
    InterventionArea createAreaForIntervention(CreateAreaForIntervention dto);
}