package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.repository.RoomRepository;
import com.plenamente.sgt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

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

}

