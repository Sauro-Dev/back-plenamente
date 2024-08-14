package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.service.InterventionAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterventionAreaServiceImpl implements InterventionAreaService {

    private final InterventionAreaRepository interventionAreaRepository;

    @Override
    public InterventionArea createAreaForIntervention(CreateAreaForIntervention dto) {
        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setName(dto.name());
        interventionArea.setDescription(dto.description());
        return interventionAreaRepository.save(interventionArea);
    }

}