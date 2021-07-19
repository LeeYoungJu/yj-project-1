package com.yjproject1.springboot.schedule.dto;

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
