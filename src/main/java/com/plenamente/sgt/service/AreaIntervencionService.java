package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.AreaIntervencionDto.RegistrarAreaPorIntervencion;
import com.plenamente.sgt.domain.entity.AreaIntervencion;

public interface AreaIntervencionService {
    AreaIntervencion registrarAreaPorIntervencion(RegistrarAreaPorIntervencion dto);
    AreaIntervencion actualizarAreaIntervencion(Long id, RegistrarAreaPorIntervencion dto);
}
