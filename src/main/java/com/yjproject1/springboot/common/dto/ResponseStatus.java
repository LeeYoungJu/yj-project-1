package com.yjproject1.springboot.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseStatus {
    private String code;
    private String msg;

    @Builder
    public ResponseStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
