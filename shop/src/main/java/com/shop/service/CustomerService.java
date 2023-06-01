package com.shop.service;

import com.shop.dto.CustomerDTO;
import com.shop.dto.CarDTO;
import com.shop.entity.CheckEntity;
import com.shop.entity.CustomerEntity;
import com.shop.entity.CarEntity;
import com.shop.entity.EmployeeEntity;
import com.shop.repository.CheckRepository;
import com.shop.repository.CustomerRepository;
import com.shop.repository.CarRepository;
import com.shop.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CheckRepository checkRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;


    public CustomerService(CustomerRepository customerRepository, CheckRepository checkRepository, CarRepository carRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.checkRepository = checkRepository;
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;

    }



    public ResponseEntity<CustomerDTO> customer(Long id) {
        try {
            return ResponseEntity.ok().body(CustomerDTO.toCustomerDto(customerRepository.findById(id).orElseThrow()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public List<CustomerDTO> customers() {
        return customerRepository.findAll().stream().map(CustomerDTO::toCustomerDto).toList();
    }


    public ResponseEntity<List<CarDTO>> getCheckCarsByCustomerId(Long customerId) {
        try {
            return ResponseEntity.ok().body(customerRepository.findById(customerId).orElseThrow().getCheck().getCars().stream().map(CarDTO::toCarDto).toList());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public CustomerDTO addCustomer(CustomerEntity customer) {
        customer.setCheck(new CheckEntity(customer.getId(), 0L, 0L, customer));
        return CustomerDTO.toCustomerDto(customerRepository.save(customer));
    }

    public ResponseEntity<CustomerDTO> updateCustomer(CustomerEntity changedCustomer, Long id) {
        try {
            CustomerEntity customer = customerRepository.findById(id).orElseThrow();
            if(changedCustomer.getFio() != null) {
                customer.setFio(changedCustomer.getFio());
            }
            if(changedCustomer.getPhNum() != null) {
                customer.setPhNum(changedCustomer.getPhNum());
            }
            return ResponseEntity.ok().body(CustomerDTO.toCustomerDto(customer));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<CustomerDTO> removeCustomer(Long id) {
        try {
            CustomerEntity customer = customerRepository.findById(id).orElseThrow();
            CheckEntity check = customer.getCheck();

            List<CarEntity> checkCars = check.getCars();

            checkCars.forEach(car -> car.getChecks().remove(check));

            customerRepository.delete(customer);

            return ResponseEntity.ok().body(CustomerDTO.toCustomerDto(customer));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
