package com.alloyfirm.tapas.rest.persistence.repository;

import com.alloyfirm.tapas.rest.persistence.IRestDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRestJpaRepository<DBO_TYPE extends IRestDBO> extends JpaRepository<DBO_TYPE,Long>, QuerydslPredicateExecutor<DBO_TYPE> {
}
