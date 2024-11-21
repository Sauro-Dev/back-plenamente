package service;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.service.impl.InterventionAreaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterventionAreaServiceTest {

    @Mock
    private InterventionAreaRepository interventionAreaRepository;

    @InjectMocks
    private InterventionAreaServiceImpl interventionAreaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAreaForIntervention() {
        // Arrange
        String name = "Area Test";
        String description = "Description Test";

        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setIdInterventionArea(1L);
        interventionArea.setName(name);
        interventionArea.setDescription(description);

        when(interventionAreaRepository.save(any(InterventionArea.class))).thenReturn(interventionArea);

        // Act
        InterventionArea result = interventionAreaService.createAreaForIntervention(name, description);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
        verify(interventionAreaRepository, times(1)).save(any(InterventionArea.class));
    }

    @Test
    void getAllInterventionAreas() {
        // Arrange
        List<InterventionArea> interventionAreas = new ArrayList<>();
        InterventionArea area1 = new InterventionArea();
        area1.setIdInterventionArea(1L);
        area1.setName("Area 1");
        area1.setDescription("Description 1");

        InterventionArea area2 = new InterventionArea();
        area2.setIdInterventionArea(2L);
        area2.setName("Area 2");
        area2.setDescription("Description 2");

        interventionAreas.add(area1);
        interventionAreas.add(area2);

        when(interventionAreaRepository.findAll()).thenReturn(interventionAreas);

        // Act
        List<InterventionArea> result = interventionAreaService.getAllInterventionAreas();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Area 1", result.get(0).getName());
        assertEquals("Area 2", result.get(1).getName());
        verify(interventionAreaRepository, times(1)).findAll();
    }
}