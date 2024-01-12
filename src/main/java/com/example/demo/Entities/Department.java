package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Department Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    private String name;
    @OneToOne
    @JoinColumn(name ="employee_id", referencedColumnName = "employee_id")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, List<Employee> employees) {
        this.name=name;
        this.employees=employees;
    }
}
