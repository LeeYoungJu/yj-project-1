package com.yjproject1.springboot.group.dto;

import com.yjproject1.domain.group.Group;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GroupDto {
    private String name;

    public Group toEntity() {
        return Group.builder().name(this.name).build();
    }
}
