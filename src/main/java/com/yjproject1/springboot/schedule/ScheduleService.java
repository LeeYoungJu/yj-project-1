package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.*;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.springboot.schedule.dto.ScheduleDayDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
import com.yjproject1.springboot.schedule.dto.ScheduleTimeDto;
import com.yjproject1.springboot.schedule.dto.UserScheduleFindDto;
import com.yjproject1.springboot.user.UserService;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final ScheduleUserRepository scheduleUserRepository;

    private final ScheduleDayRepository scheduleDayRepository;

    private final ScheduleTimeRepository scheduleTimeRepository;

    /*
     * schedule CRUD
     */
    public Schedule save(ScheduleInsertDto scheduleInsertDto) {
        return scheduleRepository.save(scheduleInsertDto.toEntity());
    }
    public List<UserScheduleFindDto> findByUser(User user) {
        List<ScheduleUser> scheduleUsers = scheduleUserRepository.findByUser(user);
        return scheduleUsers.stream().map(obj -> new UserScheduleFindDto(obj.getTitle(), obj.getDescription(), obj.getScheduleDays())).collect(Collectors.toList());
    }
    public void deleteSchedule(Long schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id).orElseThrow(() -> new IllegalArgumentException("해당 스케쥴이 존재하지 않습니다."));
        scheduleTimeRepository.deleteAllByDays(scheduleDayRepository.findBySchedule(schedule));
        scheduleDayRepository.deleteAllBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }


    /*
     * day CRUD
     */
    public Long addDayToSchedule(Long schedule_id, List<ScheduleDayDto> listDayDto) {
        Schedule schedule = scheduleRepository.findById(schedule_id).orElseThrow(() -> new IllegalArgumentException("해당 스케쥴이 존재하지 않습니다."));
        for(ScheduleDayDto scheduleDayDto : listDayDto) {
            scheduleDayRepository.save(scheduleDayDto.toEntity()).setSchedule(schedule);
        }
        return schedule_id;
    }
    public List<ScheduleDayDto> findDaysByScheduleId(Long scheduleId) {
        return scheduleDayRepository.findBySchedule(
                        scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 스케쥴이 존재하지 않습니다."))
                ).stream().map((obj) -> ScheduleDayDto.builder()
                                                .year(obj.getYear())
                                                .month(obj.getMonth())
                                                .day(obj.getDay())
                                                .description(obj.getDescription())
                                                .build()
                ).collect(Collectors.toList());
    }
    public Long updateScheduleDay(Long id, ScheduleDayDto scheduleDayDto) {
        ScheduleDay scheduleDay = scheduleDayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일자의 스케쥴이 없습니다."));
        scheduleDay.update(scheduleDayDto.getYear()
                , scheduleDayDto.getMonth()
                , scheduleDayDto.getDay()
                , scheduleDayDto.getDescription()
        );
        return id;
    }
    public void deleteDayInSchedule(User user, List<Long> listDayId) {
        scheduleTimeRepository.deleteAllByDays(scheduleDayRepository.findByIds(listDayId));
        scheduleDayRepository.deleteAllByIds(listDayId);
    }


    /*
     * time CRUD
     */
    public Long addTimeSchedule(Long dayId, ScheduleTimeDto scheduleTimeDto) {
        ScheduleDay scheduleDay = scheduleDayRepository.findById(dayId).orElseThrow(() -> new IllegalArgumentException("해당 일자의 스케쥴이 없습니다."));
        ScheduleTime scheduleTime = scheduleTimeDto.toEntity();
        ScheduleTime savedScheduleTime = scheduleTimeRepository.save(scheduleTime);
        savedScheduleTime.setScheduleDay(scheduleDay);

        return savedScheduleTime.getId();
    }
    public List<ScheduleTimeDto> findTimesInDay(Long dayId) {
        return scheduleTimeRepository.findByScheduleDay(
                scheduleDayRepository.findById(dayId).orElseThrow(() -> new IllegalArgumentException("해당 일자의 스케쥴이 없습니다."))
        ).stream().map((obj) -> ScheduleTimeDto.builder()
                .hour(obj.getHour())
                .minute(obj.getMinute())
                .description(obj.getDescription())
                .build()
        ).collect(Collectors.toList());
    }
    public Long updateScheduleTime(Long id, ScheduleTimeDto scheduleTimeDto) {
        ScheduleTime scheduleTime = scheduleTimeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 시간의 스케쥴이 없습니다."));
        scheduleTime.update(scheduleTimeDto.getHour()
                        , scheduleTimeDto.getMinute()
                        , scheduleTimeDto.getDescription()
        );
        return id;
    }
    public void deleteScheduleTime(Long id) {
        scheduleTimeRepository.delete(
                scheduleTimeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 시간의  스케쥴이 없습니다."))
        );
    }
}
