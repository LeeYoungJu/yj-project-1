package com.yjproject1.springboot.user;

import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findMy() {
        return userRepository.findByName(jwtUtil.extractUsername(jwtUtil.JWT));
    }
}
