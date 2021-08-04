package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.schedule.ScheduleUser;
import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.common.dto.ResponseDto;
import com.yjproject1.springboot.common.dto.STATUS_CODE;
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
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) {
        try {
            User user = userService.findMy();
            scheduleInsertDto.setUser(user);
            Schedule schedule = scheduleService.save(scheduleInsertDto);

            return ResponseEntity.ok(
                    ResponseDto.ok(new ScheduleInsertResponse(schedule.getId(), schedule.getTitle()))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @GetMapping("/schedule/findAll")
    public ResponseEntity<?> getUserSchedule() {
        try {
            return ResponseEntity.ok(ResponseDto.ok(scheduleService.findByUser(userService.findMy())));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @DeleteMapping("schedule")
    public ResponseEntity<?> deleteSchedule(@RequestBody Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.ok(ResponseDto.ok(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }

    /*
     * day schedule CRUD
     */
    @PostMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> addDayToSchedule(@PathVariable(name = "scheduleId") Long id, @RequestBody List<ScheduleDayDto> listDayDto) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.addDayToSchedule(id, listDayDto))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @GetMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> getDaysBySchedule(@PathVariable(name = "scheduleId") Long id) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.findDaysByScheduleId(id))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @PutMapping("schedule/day/{id}")
    public ResponseEntity<?> updateDaySchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleDayDto scheduleDayDto) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.updateScheduleDay(id, scheduleDayDto))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @DeleteMapping("schedule/day")
    public ResponseEntity<?> deleteDaySchedule(@RequestBody List<Long> ids) {
        try {
            User user = userService.findMy();
            scheduleService.deleteDayInSchedule(user, ids);
            return ResponseEntity.ok(
                    ResponseDto.ok(ids)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }

    /*
     * time schedule CRUD
     */
    @PostMapping("schedule/time/{dayId}")
    public ResponseEntity<?> insertTimeSchedule(@PathVariable(name = "dayId") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.addTimeSchedule(id, scheduleTimeDto))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @GetMapping("schedule/time/{dayId}")
    public ResponseEntity<?> getTimesByDay(@PathVariable(name = "dayId") Long id) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.findTimesInDay(id))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @PutMapping("schedule/time/{id}")
    public ResponseEntity<?> updateTimeSchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        try {
            return ResponseEntity.ok(
                    ResponseDto.ok(scheduleService.updateScheduleTime(id, scheduleTimeDto))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }
    @DeleteMapping("schedule/time/{id}")
    public ResponseEntity<?> deleteTimeSchedule(@PathVariable(name = "id") Long id) {
        try {
            scheduleService.deleteScheduleTime(id);
            return ResponseEntity.ok(
                    ResponseDto.ok(id)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseDto.bad(STATUS_CODE.BAD, e.getMessage())
            );
        }
    }

}
