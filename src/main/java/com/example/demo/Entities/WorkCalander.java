package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * WorkCalander Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkCalander {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_WorkCalander;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    LocalDateTime startTime;
    LocalDateTime endTime;
    public WorkCalander(LocalDateTime start, LocalDateTime end) {
        this.startTime = start;
        this.endTime = end;
    }
}
