package com.yjproject1.springboot.schedule;

import com.yjproject1.springboot.schedule.dto.ScheduleInsertDto;
import com.yjproject1.springboot.schedule.dto.ScheduleInsertResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @PostMapping("/schedule")
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) throws Exception {
        System.out.println(scheduleInsertDto.toString());

        return ResponseEntity.ok(new ScheduleInsertResponse(scheduleInsertDto.getTitle()));
    }
}
