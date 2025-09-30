package com.pnudataking.backend.toilet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pnudataking.backend.nursingRoom.dto.NursingRoomDetailDto;
import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import com.pnudataking.backend.nursingRoom.repository.NursingRoomRepository;
import com.pnudataking.backend.toilet.dto.ToiletDetailDto;
import com.pnudataking.backend.toilet.entity.Toilet;
import com.pnudataking.backend.toilet.repository.ToiletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToiletService {

    private final ToiletRepository toiletRepository;

    public ToiletDetailDto getDetail(Long id) {
        Toilet toilet = toiletRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("화장실을 찾을 수 없습니다. id: " + id));

        return ToiletDetailDto.from(toilet);
    }

    public List<ToiletDetailDto> getByBounds(Double swLat, Double swLng,
            Double neLat, Double neLng) {
        List<Toilet> toilets = toiletRepository.findByBounds(swLat, swLng, neLat, neLng);


        return toilets.stream()
                .filter(toilet -> {
                    return toilet.getDiaperLocationFemale() || toilet.getDiaperLocationAccessible() || toilet.getDiaperLocationMale();
                })
                .map(ToiletDetailDto::from)
                .toList();
    }
}
