package com.yjproject1.domain.user;

import com.yjproject1.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    public UserGroup findByGroupAndUser(Group group, User user);
}
