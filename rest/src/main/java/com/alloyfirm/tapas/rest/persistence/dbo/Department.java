package com.alloyfirm.tapas.rest.persistence.dbo;

import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Department implements IRestDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "department" })
    private List<Employee> employeeList;
}
