package com.example.demo.controllers.response;

import com.example.demo.dto.MeetingDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Meeting Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponse {
  List<MeetingDTO> result;
}
