package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Meeting Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime startTime;
    //This annotation of Data JPA allows to insert the Date of creation of the data
    @CreatedDate
    private Date created;
    //This annotation of Data JPA allows to insert the last Date of modification of the data
    @LastModifiedDate
    private Date updated;
    public Meeting(String meeting) {
        this.title=meeting;
    }
}
