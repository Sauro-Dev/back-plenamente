package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/register")
    public ResponseEntity<Room> registerRoom(@RequestBody Room room) {
        Room newRoom = roomService.registerRoom(room);
        return ResponseEntity.ok(newRoom);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> listRooms() {
        List<Room> rooms = roomService.listRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/therapeutic")
    public ResponseEntity<List<Room>> listRoomsByIsTherapeutic(@RequestParam boolean isTherapeutic) {
        List<Room> rooms = roomService.listRoomsByIsTherapeutic(isTherapeutic);
        return ResponseEntity.ok(rooms);
    }
}
