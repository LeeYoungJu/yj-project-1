package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.*;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
import com.yjproject1.springboot.schedule.dto.UserScheduleFindDto;
import com.yjproject1.springboot.user.UserService;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final ScheduleUserRepository scheduleUserRepository;

    public Schedule save(ScheduleInsertDto scheduleInsertDto) {
        return scheduleRepository.save(scheduleInsertDto.toEntity());
    }

    public List<UserScheduleFindDto> findByUser(User user) {
        List<ScheduleUser> scheduleUsers = scheduleUserRepository.findByUser(user);
        return scheduleUsers.stream().map(obj -> new UserScheduleFindDto(obj.getTitle(), obj.getDescription(), obj.getScheduleDays())).collect(Collectors.toList());
    }
}
