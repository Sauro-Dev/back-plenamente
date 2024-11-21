package service;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialAreaRepository;
import com.plenamente.sgt.service.impl.MaterialAreaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MaterialAreaServiceTest {
    @Mock
    private MaterialAreaRepository materialAreaRepository;

    @Mock
    private InterventionAreaRepository interventionAreaRepository;

    @InjectMocks
    private MaterialAreaServiceImpl materialAreaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAreaForMaterial() {
        // Arrange
        String interventionAreaName = "Test Area";

        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setIdInterventionArea(1L);
        interventionArea.setName(interventionAreaName);

        MaterialArea materialArea = new MaterialArea();
        materialArea.setIdMaterialArea(1L);
        materialArea.setInterventionArea(interventionArea);

        when(interventionAreaRepository.findByName(interventionAreaName)).thenReturn(Optional.of(interventionArea));
        when(materialAreaRepository.save(any(MaterialArea.class))).thenReturn(materialArea);

        // Act
        MaterialArea result = materialAreaService.createAreaForMaterial(interventionAreaName);

        // Assert
        assertNotNull(result);
        assertEquals(interventionAreaName, result.getInterventionArea().getName());
        verify(interventionAreaRepository, times(1)).findByName(interventionAreaName);
        verify(materialAreaRepository, times(1)).save(any(MaterialArea.class));
    }

    @Test
    void updateMaterialArea() {
        // Arrange
        Long materialAreaId = 1L;

        MaterialArea materialArea = new MaterialArea();
        materialArea.setIdMaterialArea(materialAreaId);

        when(materialAreaRepository.findById(materialAreaId)).thenReturn(Optional.of(materialArea));
        when(materialAreaRepository.save(any(MaterialArea.class))).thenReturn(materialArea);

        // Act
        MaterialArea result = materialAreaService.updateMaterialArea(materialAreaId);

        // Assert
        assertNotNull(result);
        assertEquals(materialAreaId, result.getIdMaterialArea());
        verify(materialAreaRepository, times(1)).findById(materialAreaId);
        verify(materialAreaRepository, times(1)).save(materialArea);
    }
}
