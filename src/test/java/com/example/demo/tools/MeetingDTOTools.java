package com.example.demo.tools;

import com.example.demo.Dto.MeetingDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeetingDTOTools {
  public static MeetingDTO createMeeting(String title) {
    return new MeetingDTO(title, LocalDateTime.now());
  }
}
