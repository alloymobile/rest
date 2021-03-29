package com.alloyfirm.tapas.rest.service.impl.project.binding;

import com.alloyfirm.tapas.rest.persistence.dbo.QProject;
import com.alloyfirm.tapas.rest.service.helper.BindingHelper;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public class ProjectBinding implements QuerydslBinderCustomizer<QProject> {
    @Override
    public void customize(QuerydslBindings querydslBindings, QProject qProject) {
        // Make case-insensitive 'like' filter for all string properties
        querydslBindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        BindingHelper.bindMultiId(querydslBindings, QProject.project.projectEmployeeList.any().employee.id, "employeeId");
    }
}
