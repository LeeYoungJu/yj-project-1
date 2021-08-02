package com.yjproject1.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Long> {

    List<ScheduleTime> findByScheduleDay(ScheduleDay scheduleDay);

    @Modifying
    @Query("delete from ScheduleTime st where st.scheduleDay in :days")
    void deleteAllByDays(@Param("days") List<ScheduleDay> days);
}
