package com.alloyfirm.tapas.rest.service.impl.project.mapper;

import com.alloyfirm.tapas.rest.persistence.dbo.Project;
import com.alloyfirm.tapas.rest.service.impl.employee.EmployeeService;
import com.alloyfirm.tapas.rest.service.impl.employeeproject.EmployeeProjectService;
import com.alloyfirm.tapas.rest.service.impl.project.dto.ProjectDTO;
import com.alloyfirm.tapas.rest.service.mapper.RestMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectMapper extends RestMapper<Project, ProjectDTO> {

    private final EmployeeService employeeService;
    private final EmployeeProjectService employeeProjectService;


    public ProjectMapper(EmployeeService employeeService,EmployeeProjectService employeeProjectService) {
        this.employeeService = employeeService;
        this.employeeProjectService = employeeProjectService;
    }

    @Override
    public void populateDBO(Project dbo, ProjectDTO dto) {
    }

    @Override
    public ProjectDTO toDTOImpl(Project dbo) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(dbo.getId());
        dto.setName(dbo.getName());
        return dto;
    }
}

