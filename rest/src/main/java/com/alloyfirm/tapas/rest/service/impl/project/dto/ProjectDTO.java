package com.alloyfirm.tapas.rest.service.impl.project.dto;

import com.alloyfirm.tapas.rest.service.dto.IRestDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO implements IRestDTO {
    private Long id;
    private String name;
}
