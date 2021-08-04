package com.yjproject1.springboot.user;

import com.yjproject1.springboot.common.dto.ResponseDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getName(), userRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            return ResponseEntity.badRequest().body(ResponseDto.bad("400", "incorrect ID or PASSWORD"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(ResponseDto.ok(new UserResponse(jwt)));
    }

}
