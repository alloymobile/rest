package com.alloyfirm.tapas.rest.service.impl.department.mapper;

import com.alloyfirm.tapas.rest.persistence.dbo.Department;
import com.alloyfirm.tapas.rest.service.impl.department.dto.DepartmentDTO;
import com.alloyfirm.tapas.rest.service.mapper.RestMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper extends RestMapper<Department, DepartmentDTO> {

    @Override
    public void populateDBO(Department dbo, DepartmentDTO dto) {
        dbo.setName(dto.getName());
    }

    @Override
    public DepartmentDTO toDTOImpl(Department dbo) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        return dto;
    }
}
