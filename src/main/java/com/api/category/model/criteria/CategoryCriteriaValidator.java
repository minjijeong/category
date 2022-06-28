package com.api.category.model.criteria;

import com.api.category.model.dto.CategoryForm;
import com.api.category.model.dto.CategoryFormValidator;
import com.api.category.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryCriteriaValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryCriteriaValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz)  {
        return (CategoryCriteriaValidator.class.isAssignableFrom(clazz));
    }


    @Override
    public void validate(Object target, Errors errors) {
        if(target == null) {
            errors.reject("998");
            return;
        }
        CategoryCriteria criteria = (CategoryCriteria) target;
        if(criteria.getId() == null){
            errors.reject("999", new String[] {"카테고리 ID"}, "empty cateId");
        }else{
            if(categoryRepository.findByIdAndDispYnIsTrue(criteria.getId()) == null){
                errors.reject("999", new String[] {"카테고리 ID"}, "invalid cateId");
            }
        }
    }
}
