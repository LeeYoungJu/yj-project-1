package com.yjproject1.springboot.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String password;

    public UserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
