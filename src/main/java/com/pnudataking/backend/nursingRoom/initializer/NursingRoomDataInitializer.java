package com.pnudataking.backend.nursingRoom.initializer;

import com.opencsv.bean.CsvToBeanBuilder;
import com.pnudataking.backend.nursingRoom.dto.NursingRoomCsvDto;
import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import com.pnudataking.backend.nursingRoom.repository.NursingRoomRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NursingRoomDataInitializer implements CommandLineRunner {

    private final NursingRoomRepository nursingRoomRepository;

    @Override
    public void run(String... args) throws Exception {
        if (nursingRoomRepository.count() > 0) {
            log.info("데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
            return;
        }

        log.info("수유실 데이터 초기화 시작...");

        List<NursingRoomCsvDto> csvList = new CsvToBeanBuilder<NursingRoomCsvDto>(
                new FileReader("src/main/resources/data/nursing_rooms.csv"))
                .withType(NursingRoomCsvDto.class)
                .build()
                .parse();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        List<NursingRoom> entities = new ArrayList<>();
        int skipCount = 0;

        for (NursingRoomCsvDto csv : csvList) {
            try {
                if (isNullOrEmpty(csv.getName()) ||
                        isNullOrEmpty(csv.getAddress()) ||
                        isNullOrEmpty(csv.getLat()) ||
                        isNullOrEmpty(csv.getLng())) {
                    skipCount++;
                    continue;
                }

                NursingRoom entity = NursingRoom.builder()
                        .name(csv.getName())
                        .address(csv.getAddress())
                        .location(csv.getLocation())
                        .tel(csv.getTel())
                        .fatherAvailable("가능".equals(csv.getFatherAvailable()))
                        .lat(Double.parseDouble(csv.getLat()))
                        .lng(Double.parseDouble(csv.getLng()))
                        .referenceDate(parseDate(csv.getReferenceDate(), formatter))
                        .build();

                entities.add(entity);  // 리스트에 모음

            } catch (Exception e) {
                log.error("데이터 처리 실패: {} - {}", csv.getName(), e.getMessage());
                skipCount++;
            }
        }

        // 한 번에 저장
        nursingRoomRepository.saveAll(entities);

        log.info("수유실 데이터 초기화 완료 - 성공: {}건, 실패: {}건", entities.size(), skipCount);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (isNullOrEmpty(dateStr)) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            log.warn("날짜 파싱 실패: {}", dateStr);
            return null;
        }
    }
}
