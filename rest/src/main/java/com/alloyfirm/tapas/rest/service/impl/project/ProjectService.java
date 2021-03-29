package com.alloyfirm.tapas.rest.service.impl.project;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Employee;
import com.alloyfirm.tapas.rest.persistence.dbo.EmployeeProject;
import com.alloyfirm.tapas.rest.persistence.dbo.Project;
import com.alloyfirm.tapas.rest.persistence.repository.ProjectRepository;
import com.alloyfirm.tapas.rest.service.impl.RestService;
import com.alloyfirm.tapas.rest.service.impl.employee.EmployeeService;
import com.alloyfirm.tapas.rest.service.impl.employeeproject.EmployeeProjectService;
import com.alloyfirm.tapas.rest.service.impl.project.dto.ProjectDTO;
import com.alloyfirm.tapas.rest.service.impl.project.mapper.ProjectMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService extends RestService<Project, ProjectDTO> {

    private final EmployeeService employeeService;
    private final EmployeeProjectService employeeProjectService;

    public ProjectService(ProjectMapper projectMapper
            , ProjectRepository projectRepository
            , EmployeeService employeeService
            , EmployeeProjectService employeeProjectService) {
        super(projectMapper, projectRepository);
        this.employeeService = employeeService;
        this.employeeProjectService = employeeProjectService;
    }

    public Optional<ResponseEntity<ProjectDTO>> readProjectById(Long id){
        return super.readById(id);
    }

    public Optional<ResponseEntity<Page<ProjectDTO>>> readAllProject(Predicate predicate, Pageable pageable){
        return super.readAll(predicate,pageable);
    }

    public void deleteProjectById(Long id) {
        super.deleteById(id);
    }

    //Used to create the project.
    public Optional<ResponseEntity<ProjectDTO>> createProject(Long employeeId,ProjectDTO newObject) {
        Optional<Employee> employee = this.employeeService.findById(employeeId);
        if(employee.isEmpty()){
            throw new NotFoundException("Employee not found");
        }
        Optional<Project> project = super.create(this.h2Mapper.toNewDBO(newObject));
        if(project.isEmpty()){
            throw new NotFoundException("Project not found");
        }
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setEmployee(employee.get());
        employeeProject.setProject(project.get());
        this.employeeProjectService.create(employeeProject);
        return Optional.of(this.h2Mapper.toDTO(project.get()));
    }

    public Optional<ResponseEntity<ProjectDTO>> updateProjectById(Long id, ProjectDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }
}
