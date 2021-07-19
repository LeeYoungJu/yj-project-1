package com.yjproject1.domain.schedule;

import com.yjproject1.domain.BaseTimeEntity;
import com.yjproject1.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
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

    @ManyToMany
    @JoinTable(
            name = "schedule_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Schedule(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
