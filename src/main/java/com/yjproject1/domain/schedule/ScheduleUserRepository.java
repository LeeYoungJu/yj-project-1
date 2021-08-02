package com.yjproject1.domain.schedule;

import com.yjproject1.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {
    List<ScheduleUser> findByUser(User user);

}
