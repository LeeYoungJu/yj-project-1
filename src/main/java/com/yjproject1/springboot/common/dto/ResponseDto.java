package com.yjproject1.springboot.common.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseDto {
    private ResponseStatus status;

    private Object data;

    public ResponseDto(ResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static ResponseEntity<ResponseDto> ok(Object data) {
        return ResponseEntity.ok(new ResponseDto(
                ResponseStatus.builder().code("200").msg("ok").build(),
                data
        ));
    }

    public static ResponseEntity<ResponseDto> bad(String code, String msg) {
        return ResponseEntity.badRequest().body(
                new ResponseDto(
                ResponseStatus.builder().code(code).msg(msg).build(),
                null
        ));
    }
}
