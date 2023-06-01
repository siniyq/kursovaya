package com.shop.controller;

import com.shop.dto.CheckDTO;
import com.shop.service.CheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    private final CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckDTO>> checks() {
        return ResponseEntity.ok().body(checkService.checks());
    }

    @PostMapping("/{checkId}/{carId}")
    public ResponseEntity<CheckDTO> addCarToCheckByCheckId(@PathVariable Long checkId, @PathVariable Long carId, @PathVariable Long employeeId) {
        return checkService.addCarToCheckByCheckId(checkId, carId, employeeId);
    }

    @DeleteMapping("/{checkId}/{carId}")
    public ResponseEntity<CheckDTO> deleteCheckCarByCarId(@PathVariable Long checkId, @PathVariable Long carId) {
        return checkService.deleteCheckCarByCarId(checkId, carId);
    }

}
