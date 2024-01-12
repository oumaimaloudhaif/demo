package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Company Entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    private String name;
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Department> departments = new ArrayList<>();

    public Company(String companyName) {
        this.name=companyName;
    }

}
