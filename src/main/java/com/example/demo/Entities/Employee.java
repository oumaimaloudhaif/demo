package com.example.demo.Entities;

import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Employee Entity
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employee_id;
    private String name;
    float salary;
    @OneToMany(mappedBy = "employee")
    private List<WorkCalander> workWorkCalanders = new ArrayList<>();
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    @ManyToMany(mappedBy = "employees")
    private List<Project> projects = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();
    @Column(name = "joining_date")
    private LocalDate joiningDate;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    public Employee(Long employee_id, String name, float salary, Gender gender, ContractType contractType) {
        this.employee_id = employee_id;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.contractType = contractType;
    }

    public Employee() {

    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
    }


    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }


    public List<WorkCalander> getWorkWorkCalanders() {
        return workWorkCalanders;
    }

    public void setWorkWorkCalanders(List<WorkCalander> workWorkCalanders) {
        this.workWorkCalanders = workWorkCalanders;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
    public int getHoursWorked() {
        return workWorkCalanders.stream()
                .mapToInt(workWorkCalander -> calculateHours(workWorkCalander.getStartTime(), workWorkCalander.getEndTime()))
                .sum();
    }
    private int calculateHours(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        int hours = (int) (seconds / 3600);
        return hours;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

