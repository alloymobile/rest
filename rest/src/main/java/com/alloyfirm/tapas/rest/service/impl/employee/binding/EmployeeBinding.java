package com.alloyfirm.tapas.rest.service.impl.employee.binding;

import com.alloyfirm.tapas.rest.persistence.dbo.QEmployee;
import com.alloyfirm.tapas.rest.service.helper.BindingHelper;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public class EmployeeBinding implements QuerydslBinderCustomizer<QEmployee> {
    @Override
    public void customize(QuerydslBindings querydslBindings, QEmployee qEmployee) {
        // Make case-insensitive 'like' filter for all string properties
        querydslBindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        BindingHelper.bindMultiId(querydslBindings, QEmployee.employee.department.id, "departmentId");
    }
}
