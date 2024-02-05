package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityListeners;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Company Entity */
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@With
@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long company_id;

  private String name;

  @OneToMany(mappedBy = "company")
  @JsonIgnore
  private List<Department> departments = new ArrayList<>();

  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;

  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;

  public Company(String companyName) {
    this.name = companyName;
  }
}
