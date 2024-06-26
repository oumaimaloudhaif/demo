package com.example.demo.controllers;

import com.example.demo.controllers.mappers.MeetingMapper;
import com.example.demo.controllers.request.MeetingRequest;
import com.example.demo.controllers.response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import com.example.demo.entities.Meeting;
import com.example.demo.servicesImpl.MeetingServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Meeting Controller */
@Validated
@RestController
public class MeetingController {
  @Autowired private MeetingServiceImpl meetingServiceImpl;
  @Autowired private MeetingMapper meetingMapper;

  /**
   * Adds a new meeting
   *
   * @param meeting the meeting object to be added
   * @return MeetingDTO
   */
  @PostMapping("/meetings")
  public MeetingDTO addMeeting(@RequestBody @Valid Meeting meeting) {
    return meetingServiceImpl.addMeeting(meeting);
  }

  /**
   * Updates an existing meeting
   *
   * @param meeting the meeting object to be updated
   * @return MeetingDTO
   */
  @PutMapping("/meetings")
  public MeetingDTO updateMeeting(@RequestBody @Valid Meeting meeting) {
    return meetingServiceImpl.updateMeeting(meeting);
  }

  /**
   * @param meetingRequest the request object containing the keyword related to the meeting
   * @return MeetingResponse
   */
  @GetMapping("/meetings")
  public MeetingResponse getMeetings(
      @RequestParam(required = false) @Valid MeetingRequest meetingRequest) {
    if (meetingRequest != null && meetingRequest.getKeyword() != null) {
      return meetingMapper.toMeetingResponse(
          meetingServiceImpl.searchMeeting(meetingRequest.getKeyword()));
    } else {
      return meetingMapper.toMeetingResponse(meetingServiceImpl.getAllMeetings());
    }
  }

  /**
   * Retrieves a meeting by its ID and deletes it.
   *
   * @param meetingId the ID of the meeting to delete
   * @return true if the meeting was successfully deleted, false otherwise
   */
  @DeleteMapping("/meetings/{id}")
  public boolean deleteMeetingById(@PathVariable("id") Long meetingId) {

    return meetingServiceImpl.deleteMeetingById(meetingId);
  }

  /**
   * Retrieves a meeting by its ID.
   *
   * @param meetingId the ID of the meeting to retrieve
   * @return MeetingDTO corresponding to the meeting, or null if the meeting does not exist
   */
  @GetMapping("/meetings/{id}")
  public MeetingDTO getMeetingById(@PathVariable("id") Long meetingId) {

    return meetingServiceImpl.getMeetingById(meetingId);
  }
}
