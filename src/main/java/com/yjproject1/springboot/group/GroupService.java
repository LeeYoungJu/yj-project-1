package com.yjproject1.springboot.group;

import com.yjproject1.domain.group.Group;
import com.yjproject1.domain.group.GroupRepository;
import com.yjproject1.domain.user.User;
import com.yjproject1.domain.user.UserGroup;
import com.yjproject1.domain.user.UserGroupRepository;
import com.yjproject1.springboot.group.dto.GroupDto;
import com.yjproject1.springboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    private final UserGroupRepository userGroupRepository;

    private final UserService userService;

    public Long createGroup(GroupDto groupDto) throws Exception {
        Group group = groupRepository.save(groupDto.toEntity());
        User user = userService.findMy();
        UserGroup userGroup = userGroupRepository.save(new UserGroup());
        userGroup.setUser(user);
        userGroup.setGroup(group);

        return group.getId();
    }
}
