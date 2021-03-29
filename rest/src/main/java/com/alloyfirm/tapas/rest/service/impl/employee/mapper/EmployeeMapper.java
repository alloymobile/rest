package com.alloyfirm.tapas.rest.service.impl.employee.mapper;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Employee;
import com.alloyfirm.tapas.rest.service.impl.department.DepartmentService;
import com.alloyfirm.tapas.rest.service.impl.employee.dto.EmployeeDTO;
import com.alloyfirm.tapas.rest.service.mapper.RestMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends RestMapper<Employee, EmployeeDTO> {

    @Override
    public void populateDBO(Employee dbo, EmployeeDTO dto) {
        dbo.setId(dto.getId());
        dbo.setName(dto.getName());
        dbo.setEmail(dto.getEmail());
    }

    @Override
    public EmployeeDTO toDTOImpl(Employee dbo) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        dto.setEmail(dbo.getEmail());
        return dto;
    }
}
