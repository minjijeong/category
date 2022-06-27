package com.api.category.controller;


import com.api.category.model.entity.Category;
import com.api.category.model.dto.CategoryForm;
import com.api.category.model.response.Json;
import com.api.category.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping("/hello")
    public Json<?> sayHello(){
        String result  = "Hello, World";
        return Json.createJson(result);
    }

    @PostMapping("/category/register")
    public Json<?> createCategory(@RequestBody CategoryForm categoryForm){
        Category response = service.save(categoryForm);
        return Json.createJson(response);
    }


    @PutMapping("/category/{id}")
    public Json<?> updateCategory(@PathVariable("id") long id, @RequestBody Category categoryForm){
        Category response = service.update(categoryForm);
        return Json.createJson(response);
    }
    @DeleteMapping("/category/{id}")
    public Json<?> deleteCategory(@PathVariable("id") long id){
        if(service.delete(id)){
            return Json.createJson(HttpStatus.OK);
        }
        return Json.createErrorJson(HttpStatus.NOT_MODIFIED.value(), HttpStatus.NOT_MODIFIED.getReasonPhrase());
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
}
