package com.shop.service;


import com.shop.dto.EmployeeDTO;
import com.shop.dto.EmployeeDTO;
import com.shop.dto.EmployeeDTO;
import com.shop.entity.*;
import com.shop.entity.EmployeeEntity;
import com.shop.repository.EmployeeRepository;
import com.shop.repository.CheckRepository;
import com.shop.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> employees() {
        return employeeRepository.findAll().stream().map(EmployeeDTO::toEmployeeDto).toList();
    }

    public EmployeeDTO addEmployee(EmployeeEntity employee) {
        return EmployeeDTO.toEmployeeDto(employeeRepository.save(employee));
    }

    public ResponseEntity<EmployeeDTO> updateEmployee(EmployeeEntity changedEmployee, Long employeeId) {
        try {
            EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow();
            if(changedEmployee.getFioemp() != null) {
                employee.setFioemp(changedEmployee.getFioemp());
            }

            return ResponseEntity.ok().body(EmployeeDTO.toEmployeeDto(employee));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
