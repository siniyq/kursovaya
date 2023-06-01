package com.shop.dto;

import com.shop.entity.CheckEntity;
import com.shop.entity.EmployeeEntity;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {

    private Long id;
    private String fioemp;
    private final List<Long> checksId;

    public EmployeeDTO(Long id, String fioemp, List<Long> checksId){
        this.id = id;
        this.fioemp = fioemp;
        this.checksId = checksId;
    }

    public static EmployeeDTO toEmployeeDto(EmployeeEntity employee){
        return new EmployeeDTO(
                employee.getId(), employee.getFioemp(), employee.getChecks().stream().map(CheckEntity::getId).toList());
    }
}
