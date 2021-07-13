package com.yjproject1.domain.schedule;

import com.yjproject1.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ScheduleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        schedule.getScheduleCategories().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
