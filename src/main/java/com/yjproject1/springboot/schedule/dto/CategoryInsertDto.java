package com.yjproject1.springboot.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryInsertDto {
    private String tag;

    @Override
    public String toString() {
        return tag;
    }
}
