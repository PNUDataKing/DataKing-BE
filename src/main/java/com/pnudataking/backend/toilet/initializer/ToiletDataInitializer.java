package com.pnudataking.backend.toilet.initializer;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;
import com.pnudataking.backend.nursingRoom.dto.NursingRoomCsvDto;
import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import com.pnudataking.backend.nursingRoom.repository.NursingRoomRepository;
import com.pnudataking.backend.toilet.dto.ToiletCsvDto;
import com.pnudataking.backend.toilet.entity.Toilet;
import com.pnudataking.backend.toilet.repository.ToiletRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToiletDataInitializer implements CommandLineRunner {

    private final ToiletRepository toiletRepository;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    @Override
    public void run(String... args) throws Exception {
        if (toiletRepository.count() > 0) {
            log.info("데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
            return;
        }

        log.info("화장실 데이터 초기화 시작...");

        List<ToiletCsvDto> csvList = new CsvToBeanBuilder<ToiletCsvDto>(
                new FileReader("src/main/resources/data/toilet.csv"))
                .withType(ToiletCsvDto.class)
                .build()
                .parse();

        List<Toilet> entities = new ArrayList<>();
        int skipCount = 0;

        for (ToiletCsvDto csv : csvList) {
            try {
                if (isNullOrEmpty(csv.getName()) ||
                        isNullOrEmpty(csv.getAddress()) ||
                        isNullOrEmpty(csv.getLat()) ||
                        isNullOrEmpty(csv.getLng())) {
                    skipCount++;
                    continue;
                }

                Toilet entity = csv.toEntity();

                entities.add(entity);  // 리스트에 모음

            } catch (Exception e) {
                log.error("데이터 처리 실패: {} - {}", csv.getName(), e.getMessage());
                skipCount++;
            }
        }

        // 한 번에 저장
        toiletRepository.saveAll(entities);

        log.info("수유실 데이터 초기화 완료 - 성공: {}건, 실패: {}건", entities.size(), skipCount);
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static LocalDate parseDate(String dateStr) {
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
