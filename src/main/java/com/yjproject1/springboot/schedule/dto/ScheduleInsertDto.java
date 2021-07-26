package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleUser;
import com.yjproject1.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleInsertDto {
    private User user;
    private String title;
    private String description;
    private List<DayInsertDto> days;
    private List<CategoryInsertDto> categories;

    public ScheduleUser toEntity() {
        ScheduleUser scheduleUser = ScheduleUser.builder()
                                        .title(this.title)
                                        .description(this.description)
                                        .build();

        scheduleUser.setUser(this.user);

        for(DayInsertDto dayInsertDto : days) {
            dayInsertDto.toEntity().setSchedule(scheduleUser);
        }
        return scheduleUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
