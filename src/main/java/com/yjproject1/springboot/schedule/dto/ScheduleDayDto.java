package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.ScheduleDay;
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

    public ScheduleDay toEntity() {
        return ScheduleDay.builder()
                .year(this.year)
                .month(this.month)
                .day(this.day)
                .description(this.description)
                .build();
    }
}
