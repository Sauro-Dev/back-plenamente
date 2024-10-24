package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.infra.repository.RoomRepository;
import com.plenamente.sgt.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> getMaterialsByRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room no encontrado con id: " + roomId));

        return materialRepository.findByRoom(room);
    }


    @Override
    public Room registerRoom(Room room) {
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (room.getAddress() == null || room.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }
        return roomRepository.save(room);
    }

    @Override
    public List<Room> listRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> listRoomsByIsTherapeutic(boolean isTherapeutic) {
        return roomRepository.findByIsTherapeutic(isTherapeutic);
    }
}