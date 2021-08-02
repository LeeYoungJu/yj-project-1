package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleUser;
import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.schedule.dto.ScheduleDayDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertResponse;
import com.yjproject1.springboot.schedule.dto.UserScheduleFindDto;
import com.yjproject1.springboot.user.UserService;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(scheduleService.findByUser(userService.findMy()));
    }

    @PostMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> addDayToSchedule(@PathVariable(name = "scheduleId") Long schedule_id, @RequestBody List<ScheduleDayDto> listDayDto) throws Exception {
        return ResponseEntity.ok(scheduleService.addDayToSchedule(schedule_id, listDayDto));
    }

    @DeleteMapping("schedule/day")
    public ResponseEntity<?> deleteDaySchedule(@RequestBody List<Long> ids) throws Exception {
        User user = userService.findMy();
        scheduleService.removeDayInSchedule(user, ids);
        return ResponseEntity.ok("delete");
    }

    @DeleteMapping("schedule")
    public ResponseEntity<?> deleteSchedule(@RequestBody Long id) {
        scheduleService.removeSchedule(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("schedule/day/{id}")
    public ResponseEntity<?> updateDaySchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleDayDto scheduleDayDto) {
        Long returnId = scheduleService.updateScheduleDay(id, scheduleDayDto);
        return ResponseEntity.ok(returnId);
    }

}
