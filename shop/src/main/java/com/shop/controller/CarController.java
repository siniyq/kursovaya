package com.shop.controller;

import com.shop.dto.CarDTO;
import com.shop.entity.CarEntity;
import com.shop.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarDTO>> cars() {
        return ResponseEntity.ok().body(carService.cars());
    }

    @PostMapping("/")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarEntity car) {
        return ResponseEntity.ok().body(carService.addCar(car));
    }

    @PatchMapping("/{carId}")
    public ResponseEntity<CarDTO> updateCar(@RequestBody CarEntity car, @PathVariable Long carId) {
        return carService.updateCar(car, carId);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<CarDTO> removeCar(@PathVariable Long carId) {
        return carService.removeCar(carId);
    }

}
