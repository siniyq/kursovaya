package com.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "check")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long countOfCars;
    private Long amount; //конечная сумма
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private CustomerEntity customer;

    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "checks", fetch = FetchType.EAGER)
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "checks", fetch = FetchType.EAGER)
    private List<CarEntity> cars = new ArrayList<>();

    public CheckEntity(Long id, Long countOfCars, Long amount, CustomerEntity customer) {
        this.id = id;
        this.countOfCars = countOfCars;
        this.amount = amount;
        this.customer = customer;
    }


}
