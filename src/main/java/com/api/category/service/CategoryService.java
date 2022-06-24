package com.api.category.service;

import com.api.category.entity.Category;
import com.api.category.repository.CategoryRepository;
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
    public Category save(Category cate) {
        // validation check
        Category result = categoryRepository.save(cate);
        return result;
    }

    @Transactional
    public Category update(Category category) {
        Category origin = categoryRepository.findByCateId(category.getCateId());
        Category result = categoryRepository.save(category);
        return result;
    }

    @Transactional
    public void delete(String cateId) {
        Category category = categoryRepository.findByCateId(cateId);
        categoryRepository.deleteById(cateId);
    }

    public Category searchCategoryById(String cateId) {
        Category category = categoryRepository.findByCateId(cateId);
        return category;
    }

    public List<Category> searchCategoryByLCateId(String lCateId) {
        List<Category> categoryList = categoryRepository.findAllByLCate(lCateId);
        return categoryList;
    }

    public List<Category> searchCategoryByMCateId(String mCateId) {
        List<Category> categoryList = categoryRepository.findAllByMCate(mCateId);
        return categoryList;
    }
}
