package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.MeetingResponse;
import com.example.demo.Entities.Meeting;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class MeetingMapper {
    public MeetingResponse toMeetingResponse(List<Meeting> meetings) {
        MeetingResponse meetingResponse = new MeetingResponse();
        meetingResponse.setResult(meetings);
        return meetingResponse;
    }
}