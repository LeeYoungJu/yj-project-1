package com.yjproject1.domain.schedule;

import com.yjproject1.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("USER")
@Entity
public class ScheduleUser extends Schedule {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.getScheduleUsers().add(this);
    }

}
