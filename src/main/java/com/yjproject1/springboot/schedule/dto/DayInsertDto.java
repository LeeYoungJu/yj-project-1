package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.ScheduleDay;
import com.yjproject1.domain.schedule.ScheduleTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DayInsertDto {
    private String year;
    private String month;
    private String day;
    private String description;
    private List<TimeInsertDto> times;

    public ScheduleDay toEntity() {
        ScheduleDay scheduleDay = ScheduleDay.builder()
                .year(this.year)
                .month(this.month)
                .day(this.day)
                .description(this.description)
                .build();

        for(TimeInsertDto timeInsertDto : this.times) {
            timeInsertDto.toEntity().setScheduleDay(scheduleDay);
        }

        return scheduleDay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(year + "-" + month + "-" + day);
        sb.append("\n");
        sb.append(description);
        for(TimeInsertDto timeInsertDto : times) {
            sb.append("\n");
            sb.append(timeInsertDto.toString());
        }
        return sb.toString();
    }
}
