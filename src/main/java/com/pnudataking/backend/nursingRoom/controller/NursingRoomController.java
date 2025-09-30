package com.pnudataking.backend.nursingRoom.controller;

import com.pnudataking.backend.nursingRoom.dto.NursingRoomDetailDto;
import com.pnudataking.backend.nursingRoom.service.NursingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
