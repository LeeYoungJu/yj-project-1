package com.yjproject1.springboot.user.dto;

import lombok.Getter;

@Getter
public class UserResponse {
    private final String jwt;

    public UserResponse(String jwt) {
        this.jwt = jwt;
    }
}
