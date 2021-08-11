package com.yjproject1.springboot.user;

import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserFriend;
import com.yjproject1.domain.user.UserFriendRepository;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.springboot.user.dto.UserInfoDto;
import com.yjproject1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserFriendRepository userFriendRepository;

    private final JwtUtil jwtUtil;

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findMy() {
        return userRepository.findByName(jwtUtil.extractUsername(jwtUtil.JWT));
    }

    public Long addFriend(Long userId, Long friendId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new IllegalArgumentException("해당 친구가 존재하지 않습니다."));

        UserFriend userFriend = new UserFriend();
        userFriend.setFriend(user, friend);

        return userFriendRepository.save(userFriend).getId();
    }
    public List<UserInfoDto> findFriends(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return userFriendRepository.findByUser(user).stream().map((obj) -> UserInfoDto
                .builder()
                .id(obj.getFriend().getId())
                .name(obj.getFriend().getName())
                .email(obj.getFriend().getEmail())
                .picture(obj.getFriend().getPicture())
                .build()).collect(Collectors.toList());
    }
    public Long deleteFriend(Long userId, Long friendId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new IllegalArgumentException("해당 친구가 존재하지 않습니다."));

        UserFriend userFriend = userFriendRepository.findByUserAndFriend(user, friend);
        userFriendRepository.delete(userFriend);
        return userFriend.getId();
    }
}
