package com.example.demo.servicesImpl;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.services.DepartmentService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Department Service Implementation
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @return List<DepartmentDTO>
     */
    public List<DepartmentDTO> getAllDepartments() {
        final List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        departments.forEach(
                department -> {
                    DepartmentDTO departmentDTO = fromDOToDTO.MapDepartment(department);
                    departmentDTOS.add(departmentDTO);
                });
        return departmentDTOS;
    }

    /**
     * @return List<DepartmentDTO>
     */

    public List<DepartmentDTO> searchDepartment(String keyword) {
        final List<Department> departments = departmentRepository.findByName(keyword);
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        departments.forEach(
                department -> {
                    DepartmentDTO departmentDTO = fromDOToDTO.MapDepartment(department);
                    departmentDTOS.add(departmentDTO);
                });
        return departmentDTOS;
    }

    /**
     * @param department
     * @return DepartmentDTO
     */
    public DepartmentDTO addDepartment(Department department) {
        final Department savedDepartment = departmentRepository.save(department);
        return fromDOToDTO.MapDepartment(savedDepartment);
    }

    /**
     * @param department
     * @return DepartmentDTO
     */
    public DepartmentDTO updateDepartment(Department department) {
        final Department updatedDepartment = departmentRepository.save(department);
        return fromDOToDTO.MapDepartment(updatedDepartment);
    }
}
