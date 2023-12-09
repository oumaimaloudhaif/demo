package com.example.demo.Services;

import com.example.demo.Entities.Department;

/**
 *
 */
public interface CompanyService {
    boolean isSkillDiverse(String skill);
    Department getMostOccupiedDepartment() ;
}
