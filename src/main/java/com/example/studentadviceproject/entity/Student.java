package com.example.studentadviceproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kodeMelli;
    private String lastName;
    private String gender;
    private String firstName;
    private int age;
   // @Pattern(regexp = "@gmail.com", message = "email must have @gmail.com")
    private String email;
    private String phone;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToOne
    @JoinColumn(name = "advice_id")
    private Advice advice;
}
