package com.yjproject1.domain.schedule;

import com.yjproject1.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {
    List<ScheduleUser> findByUser(User user);
}
