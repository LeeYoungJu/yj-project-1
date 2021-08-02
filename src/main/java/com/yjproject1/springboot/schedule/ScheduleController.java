package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleUser;
import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.schedule.dto.*;
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

    /*
     * schedule CRUD
     */
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
    @DeleteMapping("schedule")
    public ResponseEntity<?> deleteSchedule(@RequestBody Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(id);
    }

    /*
     * day schedule CRUD
     */
    @PostMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> addDayToSchedule(@PathVariable(name = "scheduleId") Long id, @RequestBody List<ScheduleDayDto> listDayDto) throws Exception {
        return ResponseEntity.ok(scheduleService.addDayToSchedule(id, listDayDto));
    }
    @GetMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> getDaysBySchedule(@PathVariable(name = "scheduleId") Long id) {
        return ResponseEntity.ok(scheduleService.findDaysByScheduleId(id));
    }
    @PutMapping("schedule/day/{id}")
    public ResponseEntity<?> updateDaySchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleDayDto scheduleDayDto) {
        return ResponseEntity.ok(
                scheduleService.updateScheduleDay(id, scheduleDayDto)
        );
    }
    @DeleteMapping("schedule/day")
    public ResponseEntity<?> deleteDaySchedule(@RequestBody List<Long> ids) throws Exception {
        User user = userService.findMy();
        scheduleService.deleteDayInSchedule(user, ids);
        return ResponseEntity.ok("delete");
    }

    /*
     * time schedule CRUD
     */
    @PostMapping("schedule/time/{dayId}")
    public ResponseEntity<?> insertTimeSchedule(@PathVariable(name = "dayId") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        return ResponseEntity.ok(
                scheduleService.addTimeSchedule(id, scheduleTimeDto)
        );
    }
    @GetMapping("schedule/time/{dayId}")
    public ResponseEntity<?> getTimesByDay(@PathVariable(name = "dayId") Long id) {
        return ResponseEntity.ok(
                scheduleService.findTimesInDay(id)
        );
    }
    @PutMapping("schedule/time/{id}")
    public ResponseEntity<?> updateTimeSchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        return ResponseEntity.ok(
                scheduleService.updateScheduleTime(id, scheduleTimeDto)
        );
    }
    @DeleteMapping("schedule/time/{id}")
    public ResponseEntity<?> deleteTimeSchedule(@PathVariable(name = "id") Long id) {
        scheduleService.deleteScheduleTime(id);
        return ResponseEntity.ok("delete");
    }

}
