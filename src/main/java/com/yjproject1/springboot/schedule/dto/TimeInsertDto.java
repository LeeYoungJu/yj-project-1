package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.ScheduleTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TimeInsertDto {
    private String hour;
    private String minute;
    private String description;

    public ScheduleTime toEntity() {
        return ScheduleTime.builder()
                .hour(this.hour)
                .minute(this.minute)
                .description(this.description)
                .build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hour + ":" + minute);
        sb.append("\n");
        sb.append(description);
        return sb.toString();
    }
}
