package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.ScheduleDay;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserScheduleFindDto {
    private String title;
    private String description;
    private List<ScheduleDayDto> days;

    public UserScheduleFindDto(String title, String description, List<ScheduleDay> days) {
        this.title = title;
        this.description = description;
        this.setDays(days);
    }

    private void setDays(List<ScheduleDay> scheduleDays) {
        this.days = scheduleDays.stream().map(obj -> ScheduleDayDto.builder()
                .year(obj.getYear())
                .month(obj.getMonth())
                .day(obj.getDay())
                .description(obj.getDescription())
                .build()
        ).collect(Collectors.toList());
    }
}
