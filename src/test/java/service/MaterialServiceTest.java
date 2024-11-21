package service;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.infra.repository.RoomRepository;
import com.plenamente.sgt.service.impl.MaterialServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private InterventionAreaRepository interventionAreaRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerMaterial() {
        // Arrange
        Material material = new Material();
        material.setName("Material A");

        Room room = new Room();
        room.setIdRoom(1L);
        room.setName("Room A");

        List<Long> interventionAreaIds = List.of(1L, 2L);

        InterventionArea area1 = new InterventionArea();
        area1.setIdInterventionArea(1L);
        area1.setName("Area 1");

        InterventionArea area2 = new InterventionArea();
        area2.setIdInterventionArea(2L);
        area2.setName("Area 2");

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(interventionAreaRepository.findById(1L)).thenReturn(Optional.of(area1));
        when(interventionAreaRepository.findById(2L)).thenReturn(Optional.of(area2));
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        // Act
        Material result = materialService.registerMaterial(material, 1L, interventionAreaIds);

        // Assert
        assertNotNull(result);
        assertEquals("Material A", result.getName());
        assertEquals("Room A", result.getRoom().getName());
        assertEquals(2, result.getMaterialAreas().size());
        verify(roomRepository, times(1)).findById(1L);
        verify(interventionAreaRepository, times(2)).findById(anyLong());
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    void getAllMaterials() {
        // Arrange
        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        when(materialRepository.findAll()).thenReturn(materials);

        // Act
        List<Material> result = materialService.getAllMaterials();

        // Assert
        assertEquals(1, result.size());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    void getMaterialById() {
        // Arrange
        Material material = new Material();
        material.setIdMaterial("M001");
        when(materialRepository.findById("M001")).thenReturn(Optional.of(material));

        // Act
        Material result = materialService.getMaterialById("M001");

        // Assert
        assertNotNull(result);
        assertEquals("M001", result.getIdMaterial());
        verify(materialRepository, times(1)).findById("M001");
    }

    @Test
    void assignMaterialToRoom() {
        // Arrange
        Material material = new Material();
        material.setIdMaterial("M001");

        Room room = new Room();
        room.setIdRoom(1L);

        when(materialRepository.findById("M001")).thenReturn(Optional.of(material));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        // Act
        Material result = materialService.assignMaterialToRoom("M001", 1L);

        // Assert
        assertNotNull(result);
        assertEquals(room, result.getRoom());
        verify(materialRepository, times(1)).findById("M001");
        verify(roomRepository, times(1)).findById(1L);
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    void unassignMaterialFromRoom() {
        // Arrange
        String materialId = "M001";

        Material material = new Material();
        material.setIdMaterial(materialId);

        Room room = new Room();
        room.setIdRoom(1L);
        material.setRoom(room);

        when(materialRepository.findById(materialId)).thenReturn(Optional.of(material));
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        // Act
        Material result = materialService.unassignMaterialFromRoom(materialId);

        // Assert
        assertNotNull(result);
        assertNull(result.getRoom(), "The room should be unassigned (null)");
        verify(materialRepository, times(1)).findById(materialId);
        verify(materialRepository, times(1)).save(material);
    }

}