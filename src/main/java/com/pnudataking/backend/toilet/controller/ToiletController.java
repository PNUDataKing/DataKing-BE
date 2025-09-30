package com.pnudataking.backend.toilet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pnudataking.backend.nursingRoom.dto.NursingRoomDetailDto;
import com.pnudataking.backend.nursingRoom.service.NursingRoomService;
import com.pnudataking.backend.toilet.dto.ToiletDetailDto;
import com.pnudataking.backend.toilet.service.ToiletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/toilets")
@RequiredArgsConstructor
public class ToiletController {

    private final ToiletService toiletService;

    @GetMapping("/{id}")
    public ResponseEntity<ToiletDetailDto> getDetail(@PathVariable Long id) {
        ToiletDetailDto detail = toiletService.getDetail(id);
        return ResponseEntity.ok(detail);
    }

    @GetMapping
    public ResponseEntity<List<ToiletDetailDto>> getByBounds(
            @RequestParam Double swLat,  // 남서쪽 위도 (왼쪽 아래)
            @RequestParam Double swLng,  // 남서쪽 경도
            @RequestParam Double neLat,  // 북동쪽 위도 (오른쪽 위)
            @RequestParam Double neLng   // 북동쪽 경도
    ) {
        List<ToiletDetailDto> rooms = toiletService.getByBounds(swLat, swLng, neLat, neLng);
        return ResponseEntity.ok(rooms);
    }
}
