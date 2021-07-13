package com.yjproject1.domain.user;

import com.yjproject1.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserFriend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    public void setFriend(User user, User friend) {
        this.user = user;
        this.friend = friend;
        user.getUserFriends().add(this);
    }
}
