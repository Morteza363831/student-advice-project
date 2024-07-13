package com.example.studentadviceproject.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "province", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<City> cities = new ArrayList<>();

}