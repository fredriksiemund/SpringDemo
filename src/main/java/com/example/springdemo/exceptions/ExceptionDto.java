package com.example.springdemo.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ExceptionDto {
    private String path;
    private Integer status;
    private LocalDateTime timesStamp;
    private List<Map<String, Object>> details;
}
