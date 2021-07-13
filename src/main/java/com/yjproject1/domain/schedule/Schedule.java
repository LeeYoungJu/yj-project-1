package com.yjproject1.domain.schedule;

import com.yjproject1.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleDay> scheduleDays = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleCategory> scheduleCategories = new ArrayList<>();

    @Builder
    public Schedule(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
