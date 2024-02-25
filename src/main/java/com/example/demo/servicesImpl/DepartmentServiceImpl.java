package com.example.demo.servicesImpl;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.services.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Department Service Implementation */
@Service
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired private DepartmentRepository departmentRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<DepartmentDTO> */
  @Override
  public List<DepartmentDTO> getAllDepartments() {
    final List<Department> departments = departmentRepository.findAll();
    List<DepartmentDTO> departmentDTOS = new ArrayList<>();
    departments.forEach(
        department -> {
          DepartmentDTO departmentDTO = fromDOToDTO.mapDepartment(department);
          departmentDTOS.add(departmentDTO);
        });
    return departmentDTOS;
  }

  /** @return List<DepartmentDTO> */
  @Override
  public List<DepartmentDTO> searchDepartment(String keyword) {
    final List<Department> departments = departmentRepository.findByName(keyword);
    List<DepartmentDTO> departmentDTOS = new ArrayList<>();
    departments.forEach(
        department -> {
          DepartmentDTO departmentDTO = fromDOToDTO.mapDepartment(department);
          departmentDTOS.add(departmentDTO);
        });
    return departmentDTOS;
  }

  /**
   * @param department the department object to be added
   * @return DepartmentDTO
   */
  @Override
  public DepartmentDTO addDepartment(Department department) {
    final Department savedDepartment = departmentRepository.save(department);
    return fromDOToDTO.mapDepartment(savedDepartment);
  }

  /**
   * @param department the department object to be updated
   * @return DepartmentDTO
   */
  @Override
  public DepartmentDTO updateDepartment(Department department) {
    final Department updatedDepartment = departmentRepository.save(department);

    return fromDOToDTO.mapDepartment(updatedDepartment);
  }

  /**
   * Retrieves a department by its ID.
   *
   * @param departmentId the ID of the department to retrieve
   * @return the DepartmentDTO corresponding to the department, or null if the department does not
   *     exist
   */
  @Override
  public DepartmentDTO getDepartmentById(Long departmentId) {
    final Department department = departmentRepository.findById(departmentId).orElse(null);
    if (department != null) {

      return fromDOToDTO.mapDepartment(department);
    } else {

      return null;
    }
  }

  /**
   * Deletes a department by its ID.
   *
   * @param departmentId the ID of the department to delete
   * @return true if the department was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteDepartmentById(Long departmentId) {
    final Department department = departmentRepository.findById(departmentId).orElse(null);
    if (department != null) {
      departmentRepository.delete(department);

      return true;
    } else {

      return false;
    }
  }
}
