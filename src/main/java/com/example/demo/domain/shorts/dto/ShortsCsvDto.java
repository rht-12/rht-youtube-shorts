package com.example.demo.domain.shorts.dto;

import com.example.demo.domain.shorts.entity.Shorts;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ShortsCsvDto {
    @CsvBindByName(column = "콘텐츠")
    private String contentId;

    @CsvBindByName(column = "동영상 제목")
    private String videoTitle;

    @CsvBindByName(column = "동영상 게시 시간")
    private String uploadDate;

    @CsvBindByName(column = "길이")
    private long videoLength;

    @CsvBindByName(column = "YouTube Premium 시청 시간(단위: 시간)")
    private BigDecimal premiumWatchHours;

    @CsvBindByName(column = "YouTube Premium 조회수")
    private long premiumViews;

    @CsvBindByName(column = "RPM (USD)")
    private float rpmUsd;

    @CsvBindByName(column = "조회수")
    private long views;

    @CsvBindByName(column = "시청 시간(단위: 분)")
    private BigDecimal watchTimeMinutes;

    @CsvBindByName(column = "대략적인 파트너 수익 (USD)")
    private float usd;

    public Shorts toEntity() {
        return Shorts.builder()
                .contentId(this.contentId)
                .videoTitle(this.videoTitle)
                .uploadDate(this.uploadDate)
                .videoLength(this.videoLength)
                .premiumWatchHours(this.premiumWatchHours)
                .premiumViews(this.premiumViews)
                .rpmUsd(this.rpmUsd)
                .views(this.views)
                .watchTimeMinutes(this.watchTimeMinutes)
                .usd(this.usd)
                .build();
    }
}
