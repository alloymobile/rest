package com.alloyfirm.tapas.rest.service.impl.employee.dto;


import com.alloyfirm.tapas.rest.service.dto.IRestDTO;
import lombok.Data;

@Data
public class EmployeeDTO implements IRestDTO {
    private Long id;
    private String name;
    private String email;
}
