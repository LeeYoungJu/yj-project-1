package com.yjproject1.domain.group;

import com.yjproject1.domain.BaseTimeEntity;
import com.yjproject1.domain.schedule.ScheduleGroup;
import com.yjproject1.domain.user.UserGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Group_DB")
public class Group extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<UserGroup> userGroups = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<ScheduleGroup> scheduleGroups = new ArrayList<>();

    @Builder
    public Group(String name) {
        this.name = name;
    }
}
