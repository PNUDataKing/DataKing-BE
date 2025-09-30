package com.pnudataking.backend.nursingRoom.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class NursingRoomCsvDto {

    @CsvBindByName(column = "기관명")
    private String name;

    @CsvBindByName(column = "주소")
    private String address;

    @CsvBindByName(column = "위치")
    private String location;

    @CsvBindByName(column = "연락처")
    private String tel;

    @CsvBindByName(column = "아빠이용")
    private String fatherAvailable;

    @CsvBindByName(column = "경도")
    private String lng;

    @CsvBindByName(column = "위도")
    private String lat;

    @CsvBindByName(column = "데이터기준일자")
    private String referenceDate;
}
