package com.example.demo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 */
@Entity
public class WorkCalander {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_WorkCalander;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    java.time.LocalDateTime startTime;
    LocalDateTime endTime;
    public WorkCalander(LocalDateTime start, LocalDateTime end) {
        this.startTime = start;
        this.endTime=end;
    }

    public WorkCalander() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
