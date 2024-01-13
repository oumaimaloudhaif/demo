package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    //This annotation of Data JPA allows to insert the Date of creation of the data
    @CreatedDate
    private Date created;
    //This annotation of Data JPA allows to insert the last Date of modification of the data
    @LastModifiedDate
    private Date updated;
    public WorkCalander(LocalDateTime start, LocalDateTime end) {
        this.startTime = start;
        this.endTime = end;
    }
}
