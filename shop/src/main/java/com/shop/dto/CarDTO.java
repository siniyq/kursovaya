package com.shop.dto;

import com.shop.entity.CheckEntity;
import com.shop.entity.CarEntity;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {
    private Long id;
    private String name;
    private int price;
    private String year;
    private List<Long> checksId;

    public CarDTO(Long id, String name, int price, String year, List<Long> checksId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.year = year;
        this.checksId = checksId;
    }

    public static CarDTO toCarDto(CarEntity car) {
        return new CarDTO(car.getId(),car.getName(), car.getPrice(), car.getYear(), car.getChecks().stream().map(CheckEntity::getId).toList());
    }
}
