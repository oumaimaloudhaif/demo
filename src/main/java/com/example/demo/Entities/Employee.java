package com.example.demo.Entities;

import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Employee Entity
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employee_id;
    private String name;
    float salary;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
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
    @JsonIgnore
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
    //This annotation of Data JPA allows to insert the Date of creation of the data
    @CreatedDate
    private Date created;
    //This annotation of Data JPA allows to insert the last Date of modification of the data
    @LastModifiedDate
    private Date updated;
    public Employee(long employee_id, String name, float salary, Gender gender, ContractType contractType) {
        this.employee_id=employee_id;
        this.name=name;
        this.salary=salary;
        this.gender=gender;
        this.contractType=contractType;
    }

    public Employee(long employee_id, String name) {
        this.employee_id=employee_id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", workWorkCalanders=" + workWorkCalanders +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                ", address=" + address +
                ", projects=" + projects +
                ", skills=" + skills +
                ", joiningDate=" + joiningDate +
                ", isActive=" + isActive +
                ", gender=" + gender +
                ", contractType=" + contractType +
                '}';
    }
}

