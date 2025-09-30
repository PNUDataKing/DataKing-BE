package com.pnudataking.backend.toilet.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "toilet")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Toilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String location;
    private String tel;
    private Double lat;
    private Double lng;

    private Integer maleChildrenToiletCount;
    private Integer femaleChildrenToiletCount;

    private Boolean diaperLocationFemale;
    private Boolean diaperLocationMale;
    private Boolean diaperLocationAccessible;
    private LocalDate referenceDate;
}