package com.yjproject1.domain.user;

import com.yjproject1.domain.BaseTimeEntity;
import com.yjproject1.domain.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public void setUser(User user) {
        this.user = user;
        user.getUserGroups().add(this);
    }

    public void setGroup(Group group) {
        this.group = group;
        group.getUserGroups().add(this);
    }

}
