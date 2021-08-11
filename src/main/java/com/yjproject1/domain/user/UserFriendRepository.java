package com.yjproject1.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {
    public UserFriend findByUserAndFriend(User user, User friend);

    public List<UserFriend> findByUser(User user);
}
