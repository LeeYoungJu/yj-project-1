package com.yjproject1.springboot.group;

import com.yjproject1.domain.group.Group;
import com.yjproject1.domain.group.GroupRepository;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserGroup;
import com.yjproject1.domain.user.UserGroupRepository;
import com.yjproject1.domain.user.UserRepository;
import com.yjproject1.springboot.group.dto.GroupDto;
import com.yjproject1.springboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public Group findById(Long groupId) throws Exception {
        return groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다."));
    }

    public Long createGroup(GroupDto groupDto) throws Exception {
        Group group = groupRepository.save(groupDto.toEntity());
        User user = userService.findMy();
        UserGroup userGroup = new UserGroup();
        userGroup.setUser(user);
        userGroup.setGroup(group);
        userGroupRepository.save(userGroup);

        return group.getId();
    }

    public void deleteGroup(Long groupId) throws Exception {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다."));
        groupRepository.delete(group);
    }

    public Long addUsersToGroup(Long groupId, List<Long> userIds) throws Exception {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다."));
        List<User> users = userRepository.findByIds(userIds);

        for(User user : users) {
            UserGroup userGroup = new UserGroup();
            userGroup.setGroup(group);
            userGroup.setUser(user);
            userGroupRepository.save(userGroup);
        }
        return groupId;
    }

    public void deleteUserInGroup(Long groupId, Long userId) throws Exception {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("해당 그룹이 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        UserGroup userGroup = userGroupRepository.findByGroupAndUser(group, user);
        userGroupRepository.delete(userGroup);
    }

}
