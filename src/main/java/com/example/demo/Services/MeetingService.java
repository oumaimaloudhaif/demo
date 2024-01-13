package com.example.demo.Services;

import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;

import java.util.List;

/**
 * Meeting Service
 */
public interface MeetingService {
   List<MeetingDTO> getAllMeetings();
   List<MeetingDTO> searchMeeting(String keyword);

   Meeting addMeeting(Meeting meeting);

  Meeting updateMeeting(Meeting meeting);

}
