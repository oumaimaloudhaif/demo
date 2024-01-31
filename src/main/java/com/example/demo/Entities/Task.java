package com.example.demo.Entities;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Task Entity */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
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
  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;
  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;

  public Task(String taskName, String description, TaskStatus taskStatus, Priority priority) {
    this.name = taskName;
    this.description = description;
    this.taskStatus = taskStatus;
    this.priority = priority;
  }
}
