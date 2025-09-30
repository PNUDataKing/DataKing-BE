package com.pnudataking.backend.nursingRoom.service;

import com.pnudataking.backend.nursingRoom.dto.NursingRoomDetailDto;
import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import com.pnudataking.backend.nursingRoom.repository.NursingRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NursingRoomService {

    private final NursingRoomRepository nursingRoomRepository;

    public NursingRoomDetailDto getDetail(Long id) {
        NursingRoom nursingRoom = nursingRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수유실을 찾을 수 없습니다. id: " + id));

        return NursingRoomDetailDto.from(nursingRoom);
    }

    public List<NursingRoomDetailDto> getByBounds(Double swLat, Double swLng,
                                                  Double neLat, Double neLng) {
        List<NursingRoom> rooms = nursingRoomRepository.findByBounds(swLat, swLng, neLat, neLng);

        return rooms.stream()
                .map(NursingRoomDetailDto::from)
                .toList();
    }
}
