package com.example.demo.controllers.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** WorkCalendar Request */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkCalendarRequest {
  @NotNull
  @NotEmpty(message = "TASK NAME NOT BE EMPTY")
  private String keyword;
}
