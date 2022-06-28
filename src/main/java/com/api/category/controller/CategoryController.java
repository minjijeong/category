package com.api.category.controller;

import com.api.category.model.criteria.CategoryCriteria;
import com.api.category.model.criteria.CategoryCriteriaValidator;
import com.api.category.model.form.CategoryForm;
import com.api.category.model.form.CategoryFormValidator;
import com.api.category.model.entity.Category;
import com.api.category.model.response.Json;
import com.api.category.service.CategoryService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryFormValidator categoryFormValidator;
    public final CategoryCriteriaValidator categoryCriteriaValidator;
    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service
                            , CategoryFormValidator categoryFormValidator
                            , CategoryCriteriaValidator categoryCriteriaValidator){
        this.service = service;
        this.categoryFormValidator = categoryFormValidator;
        this.categoryCriteriaValidator = categoryCriteriaValidator;
    }

    @GetMapping("/hello")
    public Json<?> sayHello(){
        String result  = "Hello, World";
        return Json.createJson(result);
    }

    @PostMapping("/category/register")
    public Json<?> createCategory(@RequestBody CategoryForm categoryForm, BindingResult bindingResult){

        // validation
        categoryFormValidator.validate(categoryForm, bindingResult);

        if(bindingResult.hasErrors()) {
            return setErrorMessage(bindingResult);
        }

        Category response = service.save(categoryForm);

        return Json.createJson(response);
    }


    @PutMapping("/category/{id}")
    public Json<?> updateCategory(@PathVariable("id") long id, @RequestBody CategoryForm categoryForm, BindingResult bindingResult){
        // validation
        categoryFormValidator.validate(categoryForm, bindingResult);

        if(bindingResult.hasErrors()) {
            return setErrorMessage(bindingResult);
        }

        Category response = service.update(categoryForm);
        return Json.createJson(response);
    }
    @DeleteMapping("/category/{id}")
    public Json<?> deleteCategory(@PathVariable("id") long id, @ModelAttribute CategoryCriteria criteria, BindingResult bindingResult){
        // validation
        criteria.setId(id);
        categoryCriteriaValidator.validate(criteria, bindingResult);

        if(bindingResult.hasErrors()) {
            return setErrorMessage(bindingResult);
        }

        Category response = service.delete(id);
        if(response == null){
            return Json.createErrorJson(HttpStatus.NOT_MODIFIED.value(), HttpStatus.NOT_MODIFIED.getReasonPhrase());
        }
        return Json.createJson(response);

    }

    @GetMapping("/category/{id}")
    public Json<?> getCategory(@PathVariable("id") long id){
        List<Category> response = service.searchCategoryListById(id);
        return Json.createJson(response);
    }

    @GetMapping("/category/")
    public Json<?> getCategoriesAll(){
        List<Category> response = service.searchCategoryAll();
        return Json.createJson(response);
    }

    @GetMapping("/category/all")
    public Json<?> getCategoriesAllRows(){
        List<Category> response = service.searchCategoryAll();
        return Json.createJson(response);
    }

    /**
     * validate 처리한 결과 값 에러 Json 생성
     * @param bindingResult
     * @return
     */
    private Json<?> setErrorMessage(BindingResult bindingResult){
        ObjectError error = bindingResult.getGlobalError();
        String errorMsg = Arrays.toString(error.getArguments()) + error.getDefaultMessage();
        int errorCode = Integer.valueOf(error.getCode());
        return Json.createErrorJson(errorCode,errorMsg );
    }
}
