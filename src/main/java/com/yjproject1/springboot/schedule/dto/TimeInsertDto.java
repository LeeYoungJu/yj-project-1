package com.yjproject1.springboot.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TimeInsertDto {
    private String hour;
    private String minute;
    private String description;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hour + ":" + minute);
        sb.append("\n");
        sb.append(description);
        return sb.toString();
    }
}
