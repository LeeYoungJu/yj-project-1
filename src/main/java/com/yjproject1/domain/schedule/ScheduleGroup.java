package com.yjproject1.domain.schedule;

import com.yjproject1.domain.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("GROUP")
@Entity
public class ScheduleGroup extends Schedule {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public void setGroup(Group group) {
        this.group = group;
        group.getScheduleGroups().add(this);
    }
}
