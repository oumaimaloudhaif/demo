package com.example.demo.Services;

import com.example.demo.Entities.Department;

/**
 * Company Service
 */
public interface CompanyService {
    boolean isSkillDiverse(String skill);
    Department getMostOccupiedDepartment() ;
}
