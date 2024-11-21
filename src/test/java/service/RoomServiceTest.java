package service;

import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.infra.repository.RoomRepository;
import com.plenamente.sgt.service.impl.RoomServiceImpl;
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

public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMaterialsByRoom() {
        // Arrange
        Long roomId = 1L;

        Room room = new Room();
        room.setIdRoom(roomId);
        room.setName("Test Room");

        List<Material> materials = new ArrayList<>();
        Material material = new Material();
        material.setIdMaterial("M001");
        material.setName("Material Test");
        material.setRoom(room);
        materials.add(material);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(materialRepository.findByRoom(room)).thenReturn(materials);

        // Act
        List<Material> result = roomService.getMaterialsByRoom(roomId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Material Test", result.get(0).getName());
        verify(roomRepository, times(1)).findById(roomId);
        verify(materialRepository, times(1)).findByRoom(room);
    }
    @Test
    void registerRoom() {
        // Arrange
        Room room = new Room();
        room.setName("Room Test");
        room.setAddress("Room Address");
        room.setTherapeutic(true);

        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // Act
        Room result = roomService.registerRoom(room);

        // Assert
        assertNotNull(result);
        assertEquals("Room Test", result.getName());
        assertEquals("Room Address", result.getAddress());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void listRooms() {
        // Arrange
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setIdRoom(1L);
        room.setName("Room Test");
        room.setAddress("Room Address");
        rooms.add(room);

        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<Room> result = roomService.listRooms();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Room Test", result.get(0).getName());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void listRoomsByIsTherapeutic() {
        // Arrange
        boolean isTherapeutic = true;
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setIdRoom(1L);
        room.setName("Therapeutic Room");
        room.setTherapeutic(isTherapeutic);
        rooms.add(room);

        when(roomRepository.findByIsTherapeutic(isTherapeutic)).thenReturn(rooms);

        // Act
        List<Room> result = roomService.listRoomsByIsTherapeutic(isTherapeutic);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isTherapeutic());
        verify(roomRepository, times(1)).findByIsTherapeutic(isTherapeutic);
    }

    @Test
    void getRoomById() {
        // Arrange
        Long roomId = 1L;

        Room room = new Room();
        room.setIdRoom(roomId);
        room.setName("Room Test");
        room.setAddress("Room Address");

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        Room result = roomService.getRoomById(roomId);

        // Assert
        assertNotNull(result);
        assertEquals("Room Test", result.getName());
        assertEquals("Room Address", result.getAddress());
        verify(roomRepository, times(1)).findById(roomId);
    }
}
