package com.yjproject1.springboot.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private Long id;
    private String name;
    private String email;
    private String picture;

    @Builder
    public UserInfoDto(Long id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
