package com.example.demo.domain.shorts.service;

import com.example.demo.domain.shorts.dto.ShortsCsvDto;
import com.example.demo.domain.shorts.entity.Shorts;
import com.example.demo.domain.shorts.repository.ShortsRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ShortsService")
public class ShortsService {

    private final ShortsRepository shortsRepository;

    public void postBy() {
        try (Reader reader = new InputStreamReader(new BOMInputStream(new FileInputStream("src/main/resources/csv/KRF632406889_.csv")),
                StandardCharsets.UTF_8)) {

            CsvToBean<ShortsCsvDto> csvToBean = new CsvToBeanBuilder<ShortsCsvDto>(reader)
                    .withType(ShortsCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<ShortsCsvDto> csvList = csvToBean.parse();

            // 합계 줄 제거
            csvList = csvList.stream()
                    .filter(dto -> dto.getContentId() != null && !dto.getContentId().equals("합계"))
                    .collect(Collectors.toList());

            List<Shorts> shorts = csvList.stream().map(ShortsCsvDto::toEntity).collect(Collectors.toList());

            shortsRepository.saveAll(shorts); // 저장
            log.info("총 {}개 Shorts 저장 완료", shorts.size());

        } catch (Exception e) {
            log.error("CSV 파일 읽기 실패", e);
            throw new RuntimeException("CSV 처리 실패", e);
        }
    }
}