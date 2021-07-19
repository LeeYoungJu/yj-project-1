package com.yjproject1.springboot.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleInsertDto {
    private String title;
    private String description;
    private List<DayInsertDto> days;
    private List<CategoryInsertDto> categories;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append("\n");
        sb.append(description);
        for(DayInsertDto dayInsertDto : days) {
            sb.append("\n");
            sb.append(dayInsertDto.toString());
        }
        sb.append("\n");
        for(CategoryInsertDto categoryInsertDto : categories) {
            sb.append("\n");
            sb.append(categoryInsertDto.toString());
        }
        return sb.toString();
    }
}
