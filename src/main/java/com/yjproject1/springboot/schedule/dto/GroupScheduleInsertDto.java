package com.yjproject1.springboot.schedule.dto;

import com.yjproject1.domain.group.Group;
import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleGroup;
import lombok.Getter;

import java.util.List;

@Getter
public class GroupScheduleInsertDto extends ScheduleInsertDto {
    private String title;
    private String description;
    private List<DayInsertDto> days;
    private List<CategoryInsertDto> categories;
    private Group group;

    @Override
    public Schedule toEntity() {
        ScheduleGroup scheduleGroup = ScheduleGroup.builder()
                .title(this.title)
                .description(this.description)
                .build();

        scheduleGroup.setGroup(this.group);

        for(DayInsertDto dayInsertDto : days) {
            dayInsertDto.toEntity().setSchedule(scheduleGroup);
        }

        return scheduleGroup;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
