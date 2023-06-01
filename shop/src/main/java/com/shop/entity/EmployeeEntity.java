package com.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
    @Table(name = "employee", uniqueConstraints = {
            @UniqueConstraint(columnNames = "fioemp")
    })
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fioemp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "check_empl",
            joinColumns = {@JoinColumn(name = "check_id")},
            inverseJoinColumns = {@JoinColumn(name = "empl_id")}
    )
    private List<CheckEntity> checks = new ArrayList<>();


}

