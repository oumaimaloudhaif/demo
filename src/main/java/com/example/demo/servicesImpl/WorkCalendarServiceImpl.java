package com.example.demo.servicesImpl;

import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.WorkCalendar;
import com.example.demo.repository.WorkCalendarRepository;
import com.example.demo.services.WorkCalendarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** workCalendar Service Impl */
@Service
public class WorkCalendarServiceImpl implements WorkCalendarService {
  @Autowired private WorkCalendarRepository workCalendarRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /**
   * @param keyword a keyword (tag name) to search for workCalendars
   * @return List<WorkCalendarDTO>
   */
  @Override
  public List<WorkCalendarDTO> searchWorkCalendars(String keyword) {
    final List<WorkCalendar> workCalendars = workCalendarRepository.findByTag(keyword);
    List<WorkCalendarDTO> workCalendarDTOS = new ArrayList<>();
    workCalendars.forEach(
        workCalendar -> {
          WorkCalendarDTO workCalendarDTO = fromDOToDTO.mapWorkCalendar(workCalendar);
          workCalendarDTOS.add(workCalendarDTO);
        });

    return workCalendarDTOS;
  }

  /**
   * @param workCalendar the task object to be added
   * @return WorkCalendarDTO
   */
  @Override
  public WorkCalendarDTO addWorkCalendar(WorkCalendar workCalendar) {
    final WorkCalendar savedWorkCalendar = workCalendarRepository.save(workCalendar);

    return fromDOToDTO.mapWorkCalendar(savedWorkCalendar);
  }

  /**
   * @param workCalendar the task object to be updated
   * @return WorkCalendar
   */
  @Override
  public WorkCalendarDTO updateWorkCalendar(WorkCalendar workCalendar) {
    final WorkCalendar updatedWorkCalendar = workCalendarRepository.save(workCalendar);

    return fromDOToDTO.mapWorkCalendar(updatedWorkCalendar);
  }

  /** @return List<WorkCalendarDTO> */
  @Override
  public List<WorkCalendarDTO> getAllWorkCalendars() {
    final List<WorkCalendar> workCalendars = workCalendarRepository.findAll();
    List<WorkCalendarDTO> workCalendarDTOS = new ArrayList<>();
    workCalendars.forEach(
        workCalendar -> {
          WorkCalendarDTO taskDTO = fromDOToDTO.mapWorkCalendar(workCalendar);
          workCalendarDTOS.add(taskDTO);
        });

    return workCalendarDTOS;
  }

  /**
   * Retrieves a workCalendar by its ID.
   *
   * @param workCalendarId the ID of the workCalendar to retrieve
   * @return the WorkCalendarDTO corresponding to the workCalendar, or null if the workCalendar does
   *     not exist
   */
  @Override
  public WorkCalendarDTO getWorkCalendarById(Long workCalendarId) {
    final WorkCalendar workCalendar = workCalendarRepository.findById(workCalendarId).orElse(null);
    if (workCalendar != null) {

      return fromDOToDTO.mapWorkCalendar(workCalendar);
    } else {

      return null;
    }
  }

  /**
   * Deletes a workCalendar by its ID.
   *
   * @param workCalendarId the ID of the workCalendar to delete
   * @return true if the workCalendar was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteWorkCalendarById(Long workCalendarId) {
    final WorkCalendar workCalendar = workCalendarRepository.findById(workCalendarId).orElse(null);
    if (workCalendar != null) {
      workCalendarRepository.delete(workCalendar);

      return true;
    } else {

      return false;
    }
  }
}
