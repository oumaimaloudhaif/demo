package com.example.demo.tools;

import com.example.demo.dto.MeetingDTO;
import org.springframework.stereotype.Component;

/** Meeting DTO Tools */
@Component
public class MeetingDTOTools {
  public static MeetingDTO createMeeting(String title) {

    return new MeetingDTO().withTitle(title);
  }
}
