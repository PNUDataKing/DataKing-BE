package com.pnudataking.backend.nursingRoom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nursing_room")
public class NursingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String location;

    private String tel;

    @Column(name = "father_available")
    private Boolean fatherAvailable;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;

    @Column(name = "reference_date")
    private LocalDate referenceDate;

    @Builder
    public NursingRoom(String name, String address, String location,
                       String tel, Boolean fatherAvailable,
                       Double lat, Double lng, LocalDate referenceDate) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.tel = tel;
        this.fatherAvailable = fatherAvailable;
        this.lat = lat;
        this.lng = lng;
        this.referenceDate = referenceDate;
    }
}
