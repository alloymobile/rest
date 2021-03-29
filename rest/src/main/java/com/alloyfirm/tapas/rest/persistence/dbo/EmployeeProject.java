package com.alloyfirm.tapas.rest.persistence.dbo;

import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"employee","project"})
public class EmployeeProject  implements IRestDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

}
