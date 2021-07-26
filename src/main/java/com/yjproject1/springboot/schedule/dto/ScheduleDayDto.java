package com.yjproject1.springboot.schedule.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleDayDto {
    private String year;
    private String month;
    private String day;
    private String description;

    @Builder
    public ScheduleDayDto(String year, String month, String day, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }
}
