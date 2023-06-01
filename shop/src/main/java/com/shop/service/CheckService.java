package com.shop.service;

import com.shop.dto.CheckDTO;
import com.shop.entity.CheckEntity;
import com.shop.entity.CarEntity;
import com.shop.entity.EmployeeEntity;
import com.shop.repository.CheckRepository;
import com.shop.repository.CarRepository;
import com.shop.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;

    public CheckService(CheckRepository checkRepository, CarRepository carRepository, EmployeeRepository employeeRepository) {
        this.checkRepository = checkRepository;
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<CheckDTO> checks() {

        return checkRepository.findAll().stream().map(CheckDTO::toCheckDto).toList();
    }

    public ResponseEntity<CheckDTO> addCarToCheckByCheckId(Long checkId, Long carId, Long employeeId) {
        try {
            CheckEntity check = checkRepository.findById(checkId).orElseThrow();
            CarEntity car = carRepository.findById(carId).orElseThrow();
            EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow();

            check.setCountOfCars(check.getCountOfCars() + 1);
            check.setAmount(check.getAmount() + (car.getPrice()));

            List<CheckEntity> checks = car.getChecks();
            checks.add(check);
            car.setChecks(checks);

            return ResponseEntity.ok().body(CheckDTO.toCheckDto(checkRepository.save(check)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public ResponseEntity<CheckDTO> deleteCheckCarByCarId(Long checkId, Long carId) {
        try {
            CheckEntity check = checkRepository.findById(checkId).orElseThrow();
            CarEntity car = carRepository.findById(carId).orElseThrow();

            check.setCountOfCars(check.getCountOfCars() - 1);
            check.setAmount(check.getAmount() - car.getPrice());

            List<CheckEntity> carChecks = car.getChecks();
            List<CarEntity> checkCars = check.getCars();

            carChecks.remove(check);

            checkCars.remove(car);
            check.setCars(checkCars);

            return ResponseEntity.ok().body(CheckDTO.toCheckDto(checkRepository.save(check)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

