package com.api.category.model.dto;

import com.api.category.repository.CategoryRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryFormValidator implements Validator {
    private final CategoryRepository categoryRepository;

    public CategoryFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (CategoryFormValidator.class.isAssignableFrom(clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target == null) {
            errors.reject("998");
            return;
        }

        CategoryForm categoryForm = (CategoryForm) target;

        if(categoryForm.getId() != 0){
            if(categoryRepository.findByIdAndDispYnIsTrue(categoryForm.getId()) == null){
                errors.reject("999", new String[] {"카테고리 ID"}, "invalid id");
            }
        }
        if(Strings.isBlank(categoryForm.getCateName())) {
            errors.reject("999", new String[] {"카테고리 명"}, "cateName");
        }else{
            if(categoryRepository.findByCateName(categoryForm.getCateName()) != null){
                errors.reject("999", new String[] {"카테고리 명"}, "duplicated cateName");
            }
        }

        if(categoryForm.getLevel() == 0) {
            errors.reject("999", new String[] {"레벨"}, "level");
        }
        if(categoryForm.getLevel() == 2  || categoryForm.getLevel() == 3){
            if(categoryForm.getLargeCateId() == 0){
                errors.reject("999", new String[] {"대분류 카테고리 ID"}, "largeCateId");
            }else{
                if(categoryRepository.findByIdAndLevel(categoryForm.getLargeCateId(), 1) == null){
                    errors.reject("999", new String[] {"대분류 카테고리 ID"}, "invalid largeCateId");
                }
            }
        }
        if(categoryForm.getLevel() == 3) {
            if(categoryForm.getMediumCateId() == 0) {
                errors.reject("999", new String[]{"중분류 카테고리 ID"}, "mediumCateId");
            }else {
                if (categoryRepository.findByIdAndLevel(categoryForm.getLargeCateId(), 1) == null) {
                    errors.reject("999", new String[]{"대분류 카테고리 ID"}, "invalid largeCateId");
                }
            }
        }
    }
}
