package com.example.demo.Entities;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** WorkCalander Entity */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class WorkCalendar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_WorkCalander;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  LocalDateTime startTime;
  LocalDateTime endTime;
  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;
  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;

  public WorkCalendar(LocalDateTime start, LocalDateTime end) {
    this.startTime = start;
    this.endTime = end;
  }
}
