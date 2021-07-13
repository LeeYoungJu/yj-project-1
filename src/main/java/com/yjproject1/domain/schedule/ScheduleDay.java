package com.yjproject1.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class ScheduleDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;
    private String month;
    private String day;

    private String description;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToMany(mappedBy = "day")
    private List<ScheduleTime> scheduleTimes = new ArrayList<>();

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        schedule.getScheduleDays().add(this);
    }

    @Builder
    public ScheduleDay(String year, String month, String day, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }
}
