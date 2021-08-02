package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.*;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.springboot.schedule.dto.ScheduleDayDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
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

    public Schedule save(ScheduleInsertDto scheduleInsertDto) {
        return scheduleRepository.save(scheduleInsertDto.toEntity());
    }

    public void removeSchedule(Long schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id).orElseThrow(() -> new IllegalArgumentException("해당 스케쥴이 존재하지 않습니다."));
        scheduleTimeRepository.deleteAllByDays(scheduleDayRepository.findBySchedule(schedule));
        scheduleDayRepository.deleteAllBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }

    public List<UserScheduleFindDto> findByUser(User user) {
        List<ScheduleUser> scheduleUsers = scheduleUserRepository.findByUser(user);
        return scheduleUsers.stream().map(obj -> new UserScheduleFindDto(obj.getTitle(), obj.getDescription(), obj.getScheduleDays())).collect(Collectors.toList());
    }

    public Long addDayToSchedule(Long schedule_id, List<ScheduleDayDto> listDayDto) {
        Schedule schedule = scheduleRepository.findById(schedule_id).orElseThrow(() -> new IllegalArgumentException("해당 스케쥴이 존재하지 않습니다."));
        for(ScheduleDayDto scheduleDayDto : listDayDto) {
            scheduleDayRepository.save(scheduleDayDto.toEntity()).setSchedule(schedule);
        }
        return schedule_id;
    }

    public void removeDayInSchedule(User user, List<Long> listDayId) {
        scheduleTimeRepository.deleteAllByDays(scheduleDayRepository.findByIds(listDayId));
        scheduleDayRepository.deleteAllByIds(listDayId);
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
}
