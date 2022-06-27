package com.api.category.service;

import com.api.category.model.dto.CategoryForm;
import com.api.category.model.entity.Category;
import com.api.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public List<Category> saveAll(List<Category> categoryList) {
        List<Category> result = categoryRepository.saveAllAndFlush(categoryList);
        return result;
    }

    @Transactional
    public Category save(CategoryForm form) {
        // validation check
        Long cateId = categoryRepository.getMaxCateId();
        Category request = Category.builder()
                .id(cateId)
                .cateName(form.getCateName())
                .level(form.getLevel())
                .largeCateId(form.getLargeCateId())
                .mediumCateId(form.getMediumCateId())
                .smallCateId(form.getSmallCateId())
                .build();

        Category result = categoryRepository.save(request);
        return result;
    }

    @Transactional
    public Category update(Category category) {
        Category origin = categoryRepository.findByCateId(category.getId());
        Category result = categoryRepository.save(category);
        return result;
    }

    @Transactional
    public boolean delete(long cateId) {
        Category category = categoryRepository.findByCateId(cateId);
        if(category == null) return false;
        categoryRepository.delete(category);
        return true;
    }

    public Category save(Category category) {
        // validation check
        Category result = categoryRepository.save(category);
        return result;
    }

    public Category searchCategoryById(long cateId) {
        return categoryRepository.findByCateId(cateId);
    }
    public List<Category> searchCategoryListById(long cateId) {
        List<Category> categoryList = new ArrayList<>();
        Category category = categoryRepository.findByCateId(cateId);

        if(category == null) return categoryList;

        if(category.getLevel() == 1){
            categoryList.addAll(categoryRepository.findAllByLCate(cateId));
        }else if(category.getLevel() == 2){
            categoryList.addAll(categoryRepository.findAllByMCate(cateId));
        }else{
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Category> searchCategoryByLCateId(long lCateId) {
        List<Category> categoryList = categoryRepository.findAllByLCate(lCateId);
        return categoryList;
    }

    public List<Category> searchCategoryByMCateId(long mCateId) {
        List<Category> categoryList = categoryRepository.findAllByMCate(mCateId);
        return categoryList;
    }

    public List<Category> searchCategoryAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
