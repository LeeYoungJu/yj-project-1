package com.yjproject1.springboot.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleInsertResponse {
    private Long id;
    private String title;

    public ScheduleInsertResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
