package com.alloyfirm.tapas.rest.persistence.dbo;

import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"projectEmployeeList"})
public class Project  implements IRestDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<EmployeeProject> projectEmployeeList;
}
