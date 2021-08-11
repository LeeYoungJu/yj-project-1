package com.yjproject1.springboot.user;

import com.yjproject1.springboot.common.dto.ResponseDto;
import com.yjproject1.springboot.common.dto.STATUS_CODE;
import com.yjproject1.springboot.user.dto.UserRequest;
import com.yjproject1.springboot.user.dto.UserResponse;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getName(), userRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getName());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseDto.ok(new UserResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseDto.bad(STATUS_CODE.BAD, "incorrect ID or PASSWORD");
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

    @PostMapping("/user/friend")
    public ResponseEntity<?> addFriend(@RequestParam("user_id") Long userId, @RequestParam("friend_id") Long friendId) {
        try {
            return ResponseDto.ok(userService.addFriend(userId, friendId));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @GetMapping("user/friend")
    public ResponseEntity<?> getFriends(@RequestParam("user_id") Long userId) {
        try {
            return ResponseDto.ok(userService.findFriends(userId));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }
    @DeleteMapping("/user/friend")
    public ResponseEntity<?> deleteFriend(@RequestParam("user_id") Long userId, @RequestParam("friend_id") Long friendId) {
        try {
            return ResponseDto.ok(userService.deleteFriend(userId, friendId));
        } catch (Exception e) {
            return ResponseDto.bad(STATUS_CODE.BAD, e.getMessage());
        }
    }

}
