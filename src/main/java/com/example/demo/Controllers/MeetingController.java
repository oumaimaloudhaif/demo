package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.MeetingMapper;
import com.example.demo.Controllers.Request.MeetingRequest;
import com.example.demo.Controllers.Response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import com.example.demo.entities.Meeting;
import com.example.demo.ServicesImpl.MeetingServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
public class MeetingController {
  @Autowired private MeetingServiceImpl meetingServiceImpl;
  @Autowired private MeetingMapper meetingMapper;

  /**
   *
   * @param meeting
   * @return MeetingDTO
   */
  @PostMapping("/meetings")
  public MeetingDTO addMeeting(@RequestBody @Valid Meeting meeting) {
    return meetingServiceImpl.addMeeting(meeting);
  }

  /**
   *
   * @param meeting
   * @return MeetingDTO
   */
  @PutMapping("/meetings")
  public MeetingDTO updateMeeting(@RequestBody @Valid Meeting meeting) {
    return meetingServiceImpl.updateMeeting(meeting);
  }

  /**
   *
   * @param meetingRequest
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
}
