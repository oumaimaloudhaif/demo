package com.example.demo.controllers;

import com.example.demo.controllers.mappers.WorkCalendarMapper;
import com.example.demo.controllers.request.WorkCalendarRequest;
import com.example.demo.controllers.response.WorkCalendarResponse;
import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.entities.WorkCalendar;
import com.example.demo.servicesImpl.WorkCalendarServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** WorkCalendar Controller */
@Validated
@RestController
public class WorkCalendarController {
  @Autowired private WorkCalendarServiceImpl workCalendarServiceImpl;
  @Autowired private WorkCalendarMapper workCalendarMapper;

  /**
   * Adds a new workCalendar
   *
   * @param workCalendar the task object to be added
   * @return WorkCalendarDTO
   */
  @PostMapping("/workCalendars")
  public WorkCalendarDTO addTask(@RequestBody @Valid WorkCalendar workCalendar) {

    return workCalendarServiceImpl.addWorkCalendar(workCalendar);
  }

  /**
   * Updates an existing workCalendar
   *
   * @param workCalendar the workCalendar object to be updated
   * @return WorkCalendarDTO
   */
  @PutMapping("/workCalendars")
  public WorkCalendarDTO updateTask(@RequestBody @Valid WorkCalendar workCalendar) {
    return workCalendarServiceImpl.updateWorkCalendar(workCalendar);
  }

  /**
   * @param workCalendarRequest the request object containing the keyword related to the
   *     workCalendar
   * @return WorkCalendarResponse
   */
  @GetMapping("/workCalendars")
  public WorkCalendarResponse getReports(
      @RequestParam(required = false) @Valid WorkCalendarRequest workCalendarRequest) {
    if (workCalendarRequest != null && workCalendarRequest.getKeyword() != null) {
      return workCalendarMapper.toWorkCalendarResponse(
          workCalendarServiceImpl.searchWorkCalendars(workCalendarRequest.getKeyword()));
    } else {
      return workCalendarMapper.toWorkCalendarResponse(
          workCalendarServiceImpl.getAllWorkCalendars());
    }
  }

  /**
   * Retrieves a workCalendar by its ID and deletes it.
   *
   * @param workCalendarId the ID of the workCalendar to delete
   * @return true if the workCalendar was successfully deleted, false otherwise
   */
  @DeleteMapping("/workCalendars/{id}")
  public boolean deleteTaskById(@PathVariable("id") Long workCalendarId) {

    return workCalendarServiceImpl.deleteWorkCalendarById(workCalendarId);
  }

  /**
   * Retrieves a workCalendar by its ID.
   *
   * @param workCalendarId the ID of the workCalendar to retrieve
   * @return WorkCalendarDTO corresponding to the workCalendar, or null if the workCalendar does not
   *     exist
   */
  @GetMapping("/workCalendars/{id}")
  public WorkCalendarDTO getTaskById(@PathVariable("id") Long workCalendarId) {

    return workCalendarServiceImpl.getWorkCalendarById(workCalendarId);
  }
}
