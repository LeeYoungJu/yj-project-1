package com.yjproject1.springboot.group;

import com.yjproject1.domain.user.User;
import com.yjproject1.springboot.common.dto.ResponseDto;
import com.yjproject1.springboot.common.dto.STATUS_CODE;
import com.yjproject1.springboot.group.dto.GroupDto;
import com.yjproject1.springboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<?> createGroup(@RequestBody GroupDto groupDto) {
        try {
            return ResponseDto.ok(groupService.createGroup(groupDto));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    @DeleteMapping("/group")
    public ResponseEntity<?> deleteGroup(@RequestParam("group_id") Long groupId) {
        try {
            groupService.deleteGroup(groupId);
            return ResponseDto.ok(groupId);
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    @PostMapping("/group/users/{groupId}")
    public ResponseEntity<?> addUsersToGroup(@PathVariable Long groupId, @RequestBody List<Long> userIds) {
        try {
            return ResponseDto.ok(groupService.addUsersToGroup(groupId, userIds));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @DeleteMapping("/group/user")
    public ResponseEntity<?> deleteUserInGroup(@RequestParam("group_id") Long groupId, @RequestParam("user_id") Long userId) {
        try {
            groupService.deleteUserInGroup(groupId, userId);

            return ResponseDto.ok(groupId);
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
}
