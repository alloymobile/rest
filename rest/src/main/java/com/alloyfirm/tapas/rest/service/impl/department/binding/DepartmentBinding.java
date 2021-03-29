package com.alloyfirm.tapas.rest.service.impl.department.binding;

import com.alloyfirm.tapas.rest.persistence.dbo.QDepartment;
import com.alloyfirm.tapas.rest.service.helper.BindingHelper;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public class DepartmentBinding implements QuerydslBinderCustomizer<QDepartment> {
    @Override
    public void customize(QuerydslBindings querydslBindings, QDepartment qDepartment) {
        // Make case-insensitive 'like' filter for all string properties
        querydslBindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
