package com.alloyfirm.tapas.rest.service.impl.employee;

import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.dbo.Department;
import com.alloyfirm.tapas.rest.persistence.dbo.Employee;
import com.alloyfirm.tapas.rest.persistence.repository.EmployeeRepository;
import com.alloyfirm.tapas.rest.service.impl.RestService;
import com.alloyfirm.tapas.rest.service.impl.department.DepartmentService;
import com.alloyfirm.tapas.rest.service.impl.employee.dto.EmployeeDTO;
import com.alloyfirm.tapas.rest.service.impl.employee.mapper.EmployeeMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends RestService<Employee, EmployeeDTO> {

    private final DepartmentService departmentService;

    public EmployeeService(EmployeeMapper employeeMapper
            , EmployeeRepository employeeRepository
            , DepartmentService departmentService) {
        super(employeeMapper, employeeRepository);
        this.departmentService = departmentService;
    }

    public Optional<ResponseEntity<EmployeeDTO>> readEmployeeById(Long id){
        return super.readById(id);
    }

    public Optional<ResponseEntity<Page<EmployeeDTO>>> readAllEmployee(Predicate predicate, Pageable pageable){
        return super.readAll(predicate,pageable);
    }

    public void deleteEmployeeById(Long id) {
        super.deleteById(id);
    }

    public Optional<ResponseEntity<EmployeeDTO>> createEmployee(Long departmentId, EmployeeDTO newObject) {
        Optional<Department> department = this.departmentService.findById(departmentId);
        Employee employee = this.h2Mapper.toNewDBO(newObject);
        if(department.isEmpty()){
            throw new NotFoundException("Department not found");
        }
        employee.setDepartment(department.get());
        return super.createOne(employee);
    }

    public Optional<ResponseEntity<EmployeeDTO>> updateEmployeeById(Long id, EmployeeDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }
}
