package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.AreaIntervencionDto.RegistrarAreaPorIntervencion;
import com.plenamente.sgt.domain.entity.AreaIntervencion;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.AreaIntervencionRepository;
import com.plenamente.sgt.service.AreaIntervencionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AreaIntervencionServiceImpl implements AreaIntervencionService {

    private final AreaIntervencionRepository areaIntervencionRepository;

    @Override
    public AreaIntervencion registrarAreaPorIntervencion(RegistrarAreaPorIntervencion dto) {
        AreaIntervencion areaIntervencion = new AreaIntervencion();
        areaIntervencion.setNombre(dto.nombre());
        areaIntervencion.setDescripcion(dto.descripcion());
        return areaIntervencionRepository.save(areaIntervencion);
    }

    @Override
    public AreaIntervencion actualizarAreaIntervencion(Long id, RegistrarAreaPorIntervencion dto) {
        AreaIntervencion areaIntervencion = areaIntervencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área de Intervención no encontrada con id: " + id));

        areaIntervencion.setNombre(dto.nombre());
        areaIntervencion.setDescripcion(dto.descripcion());
        return areaIntervencionRepository.save(areaIntervencion);
    }
}
