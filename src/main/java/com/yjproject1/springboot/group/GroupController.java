package com.yjproject1.springboot.group;

import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.common.dto.ResponseDto;
import com.yjproject1.springboot.common.dto.STATUS_CODE;
import com.yjproject1.springboot.group.dto.GroupDto;
import com.yjproject1.springboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GroupController {
    private final GroupService groupService;

    private final UserService userService;

    @PostMapping("/group")
    public ResponseEntity<?> createGroup(@RequestBody GroupDto groupDto) {
        try {
            return ResponseDto.ok(groupService.createGroup(groupDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
}
