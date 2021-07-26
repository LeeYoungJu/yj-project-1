package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleUser;
import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertResponse;
import com.yjproject1.springboot.schedule.dto.UserScheduleFindDto;
import com.yjproject1.springboot.user.UserService;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    private final UserService userService;

    @PostMapping("/schedule")
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) throws Exception {
        User user = userService.findMy();
        scheduleInsertDto.setUser(user);
        Schedule schedule = scheduleService.save(scheduleInsertDto);

        return ResponseEntity.ok(new ScheduleInsertResponse(schedule.getId(), schedule.getTitle()));
    }

    @GetMapping("/schedule/findAll")
    public ResponseEntity<?> getUserSchedule() throws Exception {
        User user = userService.findMy();

        return ResponseEntity.ok(scheduleService.findByUser(user));
    }

}
