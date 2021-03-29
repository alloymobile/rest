package com.alloyfirm.tapas.rest.service.impl;

import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import com.alloyfirm.tapas.rest.persistence.repository.IRestJpaRepository;
import com.alloyfirm.tapas.rest.service.RestRepositoryService;
import com.alloyfirm.tapas.rest.service.mapper.RestMapper;
import com.alloyfirm.tapas.rest.service.mapper.RestMapperPair;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Optional;

@Getter
public abstract class RestService<DBO_TYPE extends IRestDBO, DTO_TYPE extends Serializable> extends RestRepositoryService<DBO_TYPE> {

    protected final RestMapper<DBO_TYPE,DTO_TYPE> h2Mapper;

    public RestService(RestMapper<DBO_TYPE,DTO_TYPE> h2Mapper, IRestJpaRepository<DBO_TYPE> iRestJpaRepository){
        super(iRestJpaRepository);
        this.h2Mapper = h2Mapper;
    }

    protected Optional<ResponseEntity<DTO_TYPE>> readById(Long id) {
        return super.findById(id).map(this.h2Mapper::toDTO);
    }


    protected Optional<ResponseEntity<Page<DTO_TYPE>>> readAll(Predicate predicate, Pageable pageable) {
        return super.findAll(predicate,pageable).map(this.h2Mapper::toDTOs);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> createOne(DBO_TYPE dbo_type) {
        return super.create(dbo_type).map(this.h2Mapper::toDTO);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> update(DBO_TYPE dboToUpdate, DTO_TYPE updatedObject) {
        if (dboToUpdate == null || updatedObject == null) {
            return Optional.empty();
        }
        this.h2Mapper.extendForUpdate(Collections.singletonList(new RestMapperPair<>(dboToUpdate, updatedObject)));
        return this.create(dboToUpdate).map(this.h2Mapper::toDTO);
    }
}
