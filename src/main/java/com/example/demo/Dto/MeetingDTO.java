package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private String title;
    private LocalDateTime startTime;

    public MeetingDTO(String meeting) {
        this.title=meeting;
    }
}