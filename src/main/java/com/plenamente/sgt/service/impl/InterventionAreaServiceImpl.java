package com.plenamente.sgt.service.impl;

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
    public InterventionArea createAreaForIntervention(String name, String description) {
        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setName(name);
        interventionArea.setDescription(description);
        return interventionAreaRepository.save(interventionArea);
    }

}