package com.yjproject1.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ScheduleTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hour;
    private String minute;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private ScheduleDay scheduleDay;

    public void setScheduleDay(ScheduleDay scheduleDay) {
        this.scheduleDay = scheduleDay;
        scheduleDay.getScheduleTimes().add(this);
    }

    @Builder
    public ScheduleTime(String hour, String minute, String description) {
        this.hour = hour;
        this.minute = minute;
        this.description = description;
    }
}
