package com.pnudataking.backend.nursingRoom.dto;

import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class NursingRoomDetailDto {
    private Long id;
    private String name;
    private String address;
    private String location;
    private String tel;
    private Boolean fatherAvailable;
    private Double lat;
    private Double lng;
    private LocalDate referenceDate;

    public static NursingRoomDetailDto from(NursingRoom entity) {
        return NursingRoomDetailDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .location(entity.getLocation())
                .tel(entity.getTel())
                .fatherAvailable(entity.getFatherAvailable())
                .lat(entity.getLat())
                .lng(entity.getLng())
                .referenceDate(entity.getReferenceDate())
                .build();
    }
}
