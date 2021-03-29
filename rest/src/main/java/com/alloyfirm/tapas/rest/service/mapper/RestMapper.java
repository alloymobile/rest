package com.alloyfirm.tapas.rest.service.mapper;

import com.alloyfirm.tapas.rest.exception.InternalServerException;
import com.alloyfirm.tapas.rest.exception.NotFoundException;
import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class RestMapper<DBO_TYPE extends IRestDBO, DTO_TYPE extends Serializable> {

    protected abstract void populateDBO(DBO_TYPE dbo, DTO_TYPE dto);

    protected abstract DTO_TYPE toDTOImpl(DBO_TYPE dbo);

    public DBO_TYPE toNewDBO(DTO_TYPE dto) {
        final DBO_TYPE newDBOInstance = this.getNewDBOInstance();
        this.populateDBO(newDBOInstance, dto);
        return newDBOInstance;
    }

    public ResponseEntity<DTO_TYPE> toDTO(DBO_TYPE dbo) {
        return new ResponseEntity<>(toDTOImpl(dbo), HttpStatus.OK);
    }

    public ResponseEntity<Page<DTO_TYPE>> toDTOs(Page<DBO_TYPE> dbos) {
        Page<DTO_TYPE> collect = dbos.map(this::toDto);
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(collect,HttpStatus.OK);
    }

    public DTO_TYPE toDto(DBO_TYPE dbo) {
        return toDTOImpl(dbo);
    }

    private Class<DBO_TYPE> getDBOClass() {
        final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (Class<DBO_TYPE>) Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new InternalServerException("Unable to get DBO type from mapper");
        }
    }

    protected DBO_TYPE getNewDBOInstance() {
        try {
            return getDBOClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InternalServerException("Unable to instantiate class from mapper");
        }
    }

    public void extendForUpdate(Collection<RestMapperPair<DBO_TYPE, DTO_TYPE>> mcqMappedPairs) {
        mcqMappedPairs.forEach(mcqMappedPair -> this.populateDBO(mcqMappedPair.dbo, mcqMappedPair.dto));
    }
}
