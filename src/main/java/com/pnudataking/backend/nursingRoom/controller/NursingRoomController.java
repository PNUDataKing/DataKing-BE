package com.pnudataking.backend.nursingRoom.controller;

import com.pnudataking.backend.nursingRoom.dto.NursingRoomDetailDto;
import com.pnudataking.backend.nursingRoom.service.NursingRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nursing-rooms")
@RequiredArgsConstructor
public class NursingRoomController {

    private final NursingRoomService nursingRoomService;

    @GetMapping("/{id}")
    public ResponseEntity<NursingRoomDetailDto> getDetail(@PathVariable Long id) {
        NursingRoomDetailDto detail = nursingRoomService.getDetail(id);
        return ResponseEntity.ok(detail);
    }

    @GetMapping
    public ResponseEntity<List<NursingRoomDetailDto>> getByBounds(
            @RequestParam Double swLat,  // 남서쪽 위도 (왼쪽 아래)
            @RequestParam Double swLng,  // 남서쪽 경도
            @RequestParam Double neLat,  // 북동쪽 위도 (오른쪽 위)
            @RequestParam Double neLng   // 북동쪽 경도
    ) {
        List<NursingRoomDetailDto> rooms = nursingRoomService.getByBounds(swLat, swLng, neLat, neLng);
        return ResponseEntity.ok(rooms);
    }
}
