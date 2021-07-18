package com.yjproject1.domain.category;

import com.yjproject1.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @ManyToMany(mappedBy = "categories")
    private List<Schedule> schedules = new ArrayList<>();

    @Builder
    public Category(String tag) {
        this.tag = tag;
    }

}
