package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.RoomDto.AllRooms;
import com.plenamente.sgt.domain.dto.RoomDto.RegisterRoom;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/register")
    public ResponseEntity<Room> registerRoom(@RequestBody RegisterRoom dto) {

        Room room = new Room();
        room.setName(dto.name());
        room.setAddress(dto.address());
        room.setTherapeutic(dto.isTherapeutic());

        Room newRoom = roomService.registerRoom(room);
        return ResponseEntity.ok(newRoom);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllRooms>> listRooms() {
        List<Room> rooms = roomService.listRooms();
        List<AllRooms> dtoList = rooms.stream()
                .map(room -> new AllRooms(
                        room.getName(),
                        room.getAddress(),
                        room.isTherapeutic()
                ))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/therapeutic")
    public ResponseEntity<List<Room>> listRoomsByIsTherapeutic(@RequestParam boolean isTherapeutic) {
        List<Room> rooms = roomService.listRoomsByIsTherapeutic(isTherapeutic);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}/materials")
    public ResponseEntity<List<Material>> getMaterialsByRoom(@PathVariable Long roomId) {
        List<Material> materials = roomService.getMaterialsByRoom(roomId);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }
}
