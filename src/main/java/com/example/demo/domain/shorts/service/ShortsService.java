package com.example.demo.domain.shorts.service;

import com.example.demo.domain.shorts.entity.Shorts;
import com.example.demo.domain.shorts.repository.ShortsRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShortsService {

    private final ShortsRepository shortsRepository;

    public ShortsService(ShortsRepository shortsRepository) {
        this.shortsRepository = shortsRepository;
    }

    @Transactional
    public void testCsvSave() {
        try (
                Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/csv/KRF632406889.csv"), "UTF-8");
                CSVReader csvReader = new CSVReader(reader)
        ) {
            csvReader.readNext(); // 첫 줄
            csvReader.readNext(); // 두 번째 줄

            String[] nextLine;
            List<Shorts> list = new ArrayList<>();
            while ((nextLine = csvReader.readNext()) != null) {
                Shorts shorts = Shorts.builder()
                        .contentId(getStringValue(nextLine, 0))
                        .videoTitle(getStringValue(nextLine, 1))
                        .uploadDate(getStringValue(nextLine, 2))
                        .videoLength(parseLong(nextLine, 3))
                        .premiumWatchHours(parseBigDecimal(nextLine, 4))
                        .premiumViews(parseLong(nextLine, 5))
                        .rpmUsd(parseFloat(nextLine, 6))
                        .views(parseLong(nextLine, 7))
                        .watchTimeMinutes(parseBigDecimal(nextLine, 8))
                        .usd(parseFloat(nextLine, 9))
                        .build();
                list.add(shorts);
            }

            shortsRepository.saveAll(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("CSV 파일 읽기 실패", e);
        }
    }
    private String getStringValue(String[] line, int index) {
        if (index >= line.length || line[index] == null || line[index].trim().isEmpty()) {
            return "";
        }
        return line[index].trim();
    }

    private long parseLong(String[] line, int index) {
        try {
            return Long.parseLong(getStringValue(line, index));
        } catch (Exception e) {
            return 0L;
        }
    }

    private float parseFloat(String[] line, int index) {
        try {
            return Float.parseFloat(getStringValue(line, index));
        } catch (Exception e) {
            return 0.0f;
        }
    }

    private BigDecimal parseBigDecimal(String[] line, int index) {
        try {
            return new BigDecimal(getStringValue(line, index));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

}
