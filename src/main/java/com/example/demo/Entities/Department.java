package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Department Entity */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long department_id;

  private String name;

  @OneToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
  private Employee manager;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @JsonIgnore
  @OneToMany(mappedBy = "department")
  private List<Employee> employees = new ArrayList<>();
  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;
  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;

  public Department(String name, List<Employee> employees) {
    this.name = name;
    this.employees = employees;
  }
}
