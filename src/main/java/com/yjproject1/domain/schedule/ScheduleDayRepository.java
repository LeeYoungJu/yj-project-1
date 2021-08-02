package com.yjproject1.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, Long> {

    List<ScheduleDay> findBySchedule(Schedule schedule);

    @Modifying
    @Query("delete from ScheduleDay sd where sd.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

    @Query("select sd from ScheduleDay sd where sd.id in :ids")
    List<ScheduleDay> findByIds(@Param("ids") List<Long> ids);

    @Modifying
    @Query("delete from ScheduleDay sd where sd.schedule = :schedule")
    void deleteAllBySchedule(@Param("schedule") Schedule schedule);
}
