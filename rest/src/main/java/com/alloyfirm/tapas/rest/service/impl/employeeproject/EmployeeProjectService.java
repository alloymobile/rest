package com.alloyfirm.tapas.rest.service.impl.employeeproject;

import com.alloyfirm.tapas.rest.persistence.dbo.EmployeeProject;
import com.alloyfirm.tapas.rest.persistence.repository.EmployeeProjectRepository;
import com.alloyfirm.tapas.rest.service.RestRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectService  extends RestRepositoryService<EmployeeProject> {

    public EmployeeProjectService(EmployeeProjectRepository employeeProjectRepository) {
        super(employeeProjectRepository);
    }
}
