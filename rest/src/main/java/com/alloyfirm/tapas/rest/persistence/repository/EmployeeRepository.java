package com.alloyfirm.tapas.rest.persistence.repository;

import com.alloyfirm.tapas.rest.persistence.dbo.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends IRestJpaRepository<Employee> {
}
