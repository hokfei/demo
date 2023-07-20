package com.example.demo.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DemoResponseModel {
    private LocalDateTime time;
    private String message;
}
