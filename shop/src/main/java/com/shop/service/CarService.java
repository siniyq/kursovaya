package com.shop.service;

import com.shop.dto.CarDTO;
import com.shop.entity.CheckEntity;
import com.shop.entity.CarEntity;
import com.shop.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> cars() {
        return carRepository.findAll().stream().map(CarDTO::toCarDto).toList();
    }

    public CarDTO addCar(CarEntity car) {
        return CarDTO.toCarDto(carRepository.save(car));
    }

    public ResponseEntity<CarDTO> updateCar(CarEntity changedCar, Long carId) {
        try {
            CarEntity car = carRepository.findById(carId).orElseThrow();
            if(changedCar.getName() != null) {
                car.setName(changedCar.getName());
            }
            if(changedCar.getYear() != null) {
                car.setYear(changedCar.getYear());
            }
            if(changedCar.getPrice() != 0) {
                car.setPrice(changedCar.getPrice());
            }
            return ResponseEntity.ok().body(CarDTO.toCarDto(car));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<CarDTO> removeCar(Long carId) {
        try {
            CarEntity car = carRepository.findById(carId).orElseThrow();
            List<CheckEntity> carChecks = car.getChecks();

            carChecks.forEach(checkEntity -> {
                checkEntity.getCars().remove(car);
                checkEntity.setAmount(checkEntity.getAmount() - car.getPrice());
                checkEntity.setCountOfCars(checkEntity.getCountOfCars() - 1);
            });
            carRepository.delete(car);

            return ResponseEntity.ok().body(CarDTO.toCarDto(car));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
