package com.alloyfirm.tapas.rest.service.impl.department.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    private Long id;
    private String name;
}
