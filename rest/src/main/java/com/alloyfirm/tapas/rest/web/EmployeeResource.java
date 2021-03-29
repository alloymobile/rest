package com.alloyfirm.tapas.rest.web;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Employee;
import com.alloyfirm.tapas.rest.persistence.repository.EmployeeRepository;
import com.alloyfirm.tapas.rest.service.impl.employee.EmployeeService;
import com.alloyfirm.tapas.rest.service.impl.employee.binding.EmployeeBinding;
import com.alloyfirm.tapas.rest.service.impl.employee.dto.EmployeeDTO;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Employee", description = "The employee API")
@RequestMapping("employees")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.readEmployeeById(employeeId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @QuerydslPredicate(root = Employee.class,bindings = EmployeeBinding.class) Predicate predicate, Pageable pageable)
    {
        return employeeService.readAllEmployee(predicate, pageable).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestParam(name = "departmentId")Long departmentId, @RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(departmentId,employeeDTO).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                                                          @PathVariable(name="employeeId")Long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDTO).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{employeeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
