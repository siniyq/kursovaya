package com.shop.dto;

import com.shop.entity.CheckEntity;
import lombok.Data;

import java.util.List;

@Data
public class CheckDTO {
    private Long id;
    private Long cntProd;
    private Long amount;
    private List<CarDTO> cars;
    private List<EmployeeDTO> employees;

    public CheckDTO(Long id, Long cntProd, Long amount, List<CarDTO> cars, List<EmployeeDTO> employees) {
        this.id = id;
        this.cntProd = cntProd;
        this.amount = amount;
        this.cars = cars;
        this.employees = employees;
    }

    public static CheckDTO toCheckDto(CheckEntity basket) {
        return new CheckDTO(basket.getId(), basket.getCountOfCars(),
                basket.getAmount(), basket.getCars().stream().map(CarDTO::toCarDto).toList(),
                basket.getEmployees().stream().map(EmployeeDTO::toEmployeeDto).toList());
    }
}
