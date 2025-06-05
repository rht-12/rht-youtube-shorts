package com.example.demo.domain.shorts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "shorts")
public class Shorts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id", unique = true, nullable = false)
    private String contentId;

    @Column(name = "video_title", nullable = false)
    private String videoTitle;

    @Column(name = "upload_date", nullable = false)
    private String uploadDate;

    @Column(name = "video_length", nullable = false)
    private long videoLength;

    @Column(name = "premium_watch_hours", nullable = false)
    private BigDecimal premiumWatchHours;

    @Column(name = "premium_views", nullable = false)
    private long premiumViews;

    @Column(name = "rpm_usd", nullable = false)
    private float rpmUsd;

    @Column(name = "views", nullable = false)
    private long views;

    @Column(name = "watch_time_minutes", nullable = false)
    private BigDecimal watchTimeMinutes;

    @Column(name = "usd", nullable = false)
    private float usd;
}
