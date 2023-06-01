package com.shop.dto;

import com.shop.entity.CustomerEntity;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String fio;
    private String phNum;
    private CheckDTO checkDTO;

    public CustomerDTO(Long id, String fio, String phNum, CheckDTO checkDTO) {
        this.id = id;
        this.fio = fio;
        this.phNum = phNum;
        this.checkDTO = checkDTO;
    }

    public static CustomerDTO toCustomerDto(CustomerEntity customer) {
        return new CustomerDTO(customer.getId(), customer.getFio(), customer.getPhNum(), CheckDTO.toCheckDto(customer.getCheck()));
    }
}
