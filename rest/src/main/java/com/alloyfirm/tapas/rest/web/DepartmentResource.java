package com.alloyfirm.tapas.rest.web;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Department;
import com.alloyfirm.tapas.rest.service.impl.department.DepartmentService;
import com.alloyfirm.tapas.rest.service.impl.department.binding.DepartmentBinding;
import com.alloyfirm.tapas.rest.service.impl.department.dto.DepartmentDTO;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Department", description = "The work API")
@RequestMapping("departments")
public class DepartmentResource {

    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{departmentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable(name="departmentId")Long departmentId) {
        return departmentService.readDepartmentById(departmentId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<DepartmentDTO>> getAllDepartments(
            @QuerydslPredicate(root = Department.class,bindings = DepartmentBinding.class) Predicate predicate, Pageable pageable)
    {
        return departmentService.readAllDepartment(predicate, pageable).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.createDepartment(departmentDTO).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/{departmentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,
                                                          @PathVariable(name="departmentId")Long departmentId){
        return departmentService.updateDepartmentById(departmentId, departmentDTO).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{departmentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteDepartment(@PathVariable(name="departmentId")Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
