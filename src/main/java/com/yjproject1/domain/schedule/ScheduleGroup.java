package com.yjproject1.domain.schedule;

import com.yjproject1.domain.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("GROUP")
@Entity
public class ScheduleGroup extends Schedule {

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public void setGroup(Group group) {
        this.group = group;
        group.getScheduleGroups().add(this);
    }

    @Builder
    public ScheduleGroup(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
