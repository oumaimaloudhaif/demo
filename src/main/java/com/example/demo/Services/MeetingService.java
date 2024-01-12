package com.example.demo.Services;

import com.example.demo.Entities.Meeting;

import java.util.List;

/**
 * Meeting Service
 */
public interface MeetingService {
   List<Meeting> getAllMeetings();
   List<Meeting> searchMeeting(String keyword);

   Meeting addMeeting(Meeting meeting);

  Meeting updateMeeting(Meeting meeting);

}
