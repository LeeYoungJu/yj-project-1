package com.yjproject1.springboot.schedule;

import com.yjproject1.domain.group.Group;
import com.yjproject1.domain.schedule.Schedule;
import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.common.dto.ResponseDto;
import com.yjproject1.springboot.common.dto.STATUS_CODE;
import com.yjproject1.springboot.group.GroupService;
import com.yjproject1.springboot.schedule.dto.*;
import com.yjproject1.springboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    private final UserService userService;
    private final GroupService groupService;

    /*
     * schedule CRUD
     */
    @PostMapping("/schedule")
    public ResponseEntity<?> postSchedule(@RequestBody UserScheduleInsertDto userScheduleInsertDto) {
        try {
            User user = userService.findMy();
            userScheduleInsertDto.setUser(user);
            Schedule schedule = scheduleService.save(userScheduleInsertDto);

            return ResponseDto.ok(new ScheduleInsertResponse(schedule.getId(), schedule.getTitle()));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @GetMapping("/schedule/findAll")
    public ResponseEntity<?> getUserSchedule() {
        try {
            return ResponseDto.ok(scheduleService.findByUser(userService.findMy()));
        } catch(Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @DeleteMapping("schedule")
    public ResponseEntity<?> deleteSchedule(@RequestBody Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseDto.ok(id);
        } catch(Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    /*
     * day schedule CRUD
     */
    @PostMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> addDayToSchedule(@PathVariable(name = "scheduleId") Long id, @RequestBody List<ScheduleDayDto> listDayDto) {
        try {
            return ResponseDto.ok(scheduleService.addDayToSchedule(id, listDayDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @GetMapping("/schedule/day/{scheduleId}")
    public ResponseEntity<?> getDaysBySchedule(@PathVariable(name = "scheduleId") Long id) {
        try {
            return ResponseDto.ok(scheduleService.findDaysByScheduleId(id));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @PutMapping("schedule/day/{id}")
    public ResponseEntity<?> updateDaySchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleDayDto scheduleDayDto) {
        try {
            return ResponseDto.ok(scheduleService.updateScheduleDay(id, scheduleDayDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @DeleteMapping("schedule/day")
    public ResponseEntity<?> deleteDaySchedule(@RequestBody List<Long> ids) {
        try {
            User user = userService.findMy();
            scheduleService.deleteDayInSchedule(user, ids);
            return ResponseDto.ok(ids);
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    /*
     * time schedule CRUD
     */
    @PostMapping("schedule/time/{dayId}")
    public ResponseEntity<?> insertTimeSchedule(@PathVariable(name = "dayId") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        try {
            return ResponseDto.ok(scheduleService.addTimeSchedule(id, scheduleTimeDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @GetMapping("schedule/time/{dayId}")
    public ResponseEntity<?> getTimesByDay(@PathVariable(name = "dayId") Long id) {
        try {
            return ResponseDto.ok(scheduleService.findTimesInDay(id));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @PutMapping("schedule/time/{id}")
    public ResponseEntity<?> updateTimeSchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleTimeDto scheduleTimeDto) {
        try {
            return ResponseDto.ok(scheduleService.updateScheduleTime(id, scheduleTimeDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @DeleteMapping("schedule/time/{id}")
    public ResponseEntity<?> deleteTimeSchedule(@PathVariable(name = "id") Long id) {
        try {
            scheduleService.deleteScheduleTime(id);
            return ResponseDto.ok(id);
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    /*
     * group schedule CRUD
     */
    @PostMapping("/group/schedule/{group_id}")
    public ResponseEntity<?> postGroupSchedule(@PathVariable(name = "group_id") Long groupId, @RequestBody GroupScheduleInsertDto groupScheduleInsertDto) {
        try {
            Group group = groupService.findById(groupId);
            groupScheduleInsertDto.setGroup(group);
            Schedule schedule = scheduleService.saveGroupSchedule(groupScheduleInsertDto);

            return ResponseDto.ok(new ScheduleInsertResponse(schedule.getId(), schedule.getTitle()));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

}
