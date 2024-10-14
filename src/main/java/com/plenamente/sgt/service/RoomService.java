package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.Room;

import java.util.List;

public interface RoomService {
    Room registerRoom(Room room);
    List<Room> listRooms();
    List<Room> listRoomsByIsTherapeutic(boolean isTherapeutic);
}
