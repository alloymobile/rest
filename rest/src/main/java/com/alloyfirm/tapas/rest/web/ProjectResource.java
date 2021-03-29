package com.alloyfirm.tapas.rest.web;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Project;
import com.alloyfirm.tapas.rest.service.impl.project.ProjectService;
import com.alloyfirm.tapas.rest.service.impl.project.binding.ProjectBinding;
import com.alloyfirm.tapas.rest.service.impl.project.dto.ProjectDTO;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Project", description = "The project API")
@RequestMapping("projects")
public class ProjectResource {

    private final ProjectService projectService;

    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{projectId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjectDTO> getProject(@PathVariable(name="projectId")Long projectId) {
        return projectService.readProjectById(projectId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(
            @QuerydslPredicate(root = Project.class,bindings = ProjectBinding.class) Predicate predicate, Pageable pageable)
    {
        return projectService.readAllProject(predicate, pageable).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectDTO> saveProject(@RequestParam(name = "employeeId")Long employeeId,@RequestBody ProjectDTO projectDTO){
        return projectService.createProject(employeeId,projectDTO).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/{projectId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO,
                                                      @PathVariable(name="projectId")Long projectId){
        return projectService.updateProjectById(projectId, projectDTO).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{projectId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteProject(@PathVariable(name="projectId")Long projectId){
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
