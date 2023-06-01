package com.shop.controller;

import com.shop.dto.CarDTO;
import com.shop.dto.EmployeeDTO;
import com.shop.entity.EmployeeEntity;
import com.shop.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService){ 
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> employee(){
        return ResponseEntity.ok().body(employeeService.employees());
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeEntity employee) {
        return ResponseEntity.ok().body(employeeService.addEmployee(employee));
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeEntity employee, @PathVariable Long employeeId) {
        return employeeService.updateEmployee(employee, employeeId);
    }

}
