package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.ScheduleTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleTimeDto {
    private String hour;
    private String minute;
    private String description;

    @Builder
    public ScheduleTimeDto(String hour, String minute, String description) {
        this.hour = hour;
        this.minute = minute;
        this.description = description;
    }

    public ScheduleTime toEntity() {
        return ScheduleTime.builder()
                .hour(this.hour)
                .minute(this.minute)
                .description(this.description)
                .build();
    }
}
