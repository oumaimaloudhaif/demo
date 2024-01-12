package com.example.demo.Entities;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Task Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Task;

    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(String taskName,String description, TaskStatus taskStatus, Priority priority) {
        this.name=taskName;
        this.description=description;
        this.taskStatus=taskStatus;
        this.priority=priority;
    }
}