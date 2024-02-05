package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/*** Meeting Mapper ***/
@Component
public class MeetingMapper {
  public MeetingResponse toMeetingResponse(List<MeetingDTO> meetings) {
    MeetingResponse meetingResponse = new MeetingResponse();
    meetingResponse.setResult(meetings);

    return meetingResponse;
  }
}
