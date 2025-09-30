package com.pnudataking.backend.toilet.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.pnudataking.backend.toilet.entity.Toilet;
import com.pnudataking.backend.toilet.initializer.ToiletDataInitializer;

import lombok.Data;

@Data
public class ToiletCsvDto {

    @CsvBindByName(column = "화장실명")
    private String name;

    @CsvBindByName(column = "지번주소")
    private String address;

    @CsvBindByName(column = "도로명 상세주소")
    private String location;

    @CsvBindByName(column = "관리기관 전화번호")
    private String tel;

    @CsvBindByName(column = "경도")
    private String lng;

    @CsvBindByName(column = "위도")
    private String lat;

    @CsvBindByName(column = "데이터기준일자")
    private String referenceDate;

    @CsvBindByName(column = "남성용-어린이용소변기수")
    private Integer maleChildrenToiletCount;

    @CsvBindByName(column = "여성용-어린이용대변기수")
    private Integer femaleChildrenToiletCount;

    @CsvBindByName(column = "기저귀교환대장소")
    private String diaperLocation;

    public Toilet toEntity() {
        return new Toilet(
                null,
                name,
                address,
                location,
                tel,
                Double.valueOf(lat),
                Double.valueOf(lng),
                maleChildrenToiletCount,
                femaleChildrenToiletCount,
                diaperLocation.contains("여성용화장실"),
                diaperLocation.contains("남성용화장실"),
                diaperLocation.contains("장애인화장실"),
                ToiletDataInitializer.parseDate(referenceDate)
        );
    }
}