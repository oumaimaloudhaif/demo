package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.MeetingMapper;
import com.example.demo.Controllers.Request.MeetingRequest;
import com.example.demo.Controllers.Response.MeetingResponse;
import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;
import com.example.demo.ServicesImpl.MeetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
public class MeetingController {
    @Autowired
    private MeetingServiceImpl meetingServiceImpl;
    @Autowired
    private MeetingMapper meetingMapper;

    /**
     *
     * @return
     */
/*
    @GetMapping("/meetings")
    public MeetingResponse getAllMeetings() {
        return meetingMapper.toMeetingResponse(meetingServiceImpl.getAllMeetings());
    }
*/

    /**
     *
     *
     *@return Meeting
     */
    @PostMapping("/meetings")
    public MeetingDTO addMeeting(@RequestBody @Valid Meeting meeting) {
        return meetingServiceImpl.addMeeting(meeting);
    }
    /**
     *
     *
     *@return Meeting
     */
    @PutMapping("/meetings")
    public MeetingDTO updateMeeting(@RequestBody @Valid Meeting meeting) {
        return meetingServiceImpl.updateMeeting(meeting);
    }
    /**
     *
     * @return MeetingResponse
     */
   /* @GetMapping("/meetings")
    public MeetingResponse searchMeeting(@RequestParam(required = false) @Valid MeetingRequest meetingRequest) {
        return meetingMapper.toMeetingResponse(meetingServiceImpl.searchMeeting(meetingRequest.getKeyword()));
    }*/

    @GetMapping("/meetings")
    public MeetingResponse getMeetings(@RequestParam(required = false) @Valid MeetingRequest meetingRequest) {
        if (meetingRequest != null && meetingRequest.getKeyword() != null) {
            return meetingMapper.toMeetingResponse(meetingServiceImpl.searchMeeting(meetingRequest.getKeyword()));
        } else {
            return meetingMapper.toMeetingResponse(meetingServiceImpl.getAllMeetings());
        }
    }
}