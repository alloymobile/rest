package com.alloyfirm.tapas.rest.service.mapper;


import com.alloyfirm.tapas.rest.persistence.IRestDBO;

import java.io.Serializable;

public class RestMapperPair<DBO_TYPE extends IRestDBO, DTO_TYPE extends Serializable> {
    public final DBO_TYPE dbo;
    public final DTO_TYPE dto;

    public RestMapperPair(DBO_TYPE dbo_type,DTO_TYPE dto_type) {
        this.dbo = dbo_type;
        this.dto = dto_type;
    }
}