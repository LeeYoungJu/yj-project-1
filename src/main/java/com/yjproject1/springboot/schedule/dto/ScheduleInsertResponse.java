package com.yjproject1.springboot.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleInsertResponse {
    private String title;

    public ScheduleInsertResponse(String title) {
        this.title = title;
    }
}
