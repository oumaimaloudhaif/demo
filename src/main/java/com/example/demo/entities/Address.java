package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Address Entity */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long address_id;

  private String street;

  private String city;

  private String postalCode;

  @OneToOne(mappedBy = "address")
  @JsonIgnore
  private Employee employee;

  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;

  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;

  public Address(String street, String city, String postalCode) {
    this.street = street;
    this.city = city;
    this.postalCode = postalCode;
  }
}
