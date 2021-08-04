package com.yjproject1.springboot.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto {
    private ResponseStatus status;

    private Object data;

    public ResponseDto(ResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static ResponseDto ok(Object data) {
        return new ResponseDto(
                ResponseStatus.builder().code("200").msg("ok").build(),
                data
        );
    }

    public static ResponseDto bad(String code, String msg) {
        return new ResponseDto(
                ResponseStatus.builder().code(code).msg(msg).build(),
                null
        );
    }
}
