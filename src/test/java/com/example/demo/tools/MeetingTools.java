package com.example.demo.tools;

import com.example.demo.Entities.Meeting;
import org.springframework.stereotype.Component;


@Component
public class MeetingTools {
  public static Meeting createMeeting(Long id, String title) {
    return new Meeting().withId(id).withTitle(title);
  }

}
