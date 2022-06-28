package com.api.category.service;

import com.api.category.model.form.CategoryForm;
import com.api.category.model.entity.Category;
import com.api.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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

    /**
     * 카테고리 생성
     * @param form
     * @return
     */
    @Transactional
    public Category save(CategoryForm form) {
        Long cateId = categoryRepository.getMaxCateId();

        Category category = new Category();
        BeanUtils.copyProperties(form,category);
        category.setId(cateId);

        Category result = categoryRepository.save(category);
        return result;
    }

    /**
     * 카테고리 수정
     * @param form
     * @return
     */
    @Transactional
    public Category update(CategoryForm form) {
        Category category = new Category();
        BeanUtils.copyProperties(form,category);
        Category result = categoryRepository.save(category);
        return result;
    }

    /**
     * 카테고리 삭제
     * - 하위 카테고리 존재 시, 미전시 처리
     * - 하위 카테고리 미 존재 시 삭제 처리
     * @param cateId
     * @return
     */
    @Transactional
    public Category delete(long cateId) {
        Category category = categoryRepository.findByIdAndDispYnIsTrue(cateId);
        if(category != null){
            if(category.getLevel() != 3) {
                // 하위 카테고리 존재여부 없을 경우 데이타 삭제
                if((category.getLevel() == 1 && categoryRepository.findAllByLargeCateIdAndDispYnIsTrue(cateId).size() == 0)
                    || (category.getLevel() == 2 && categoryRepository.findAllByMediumCateIdAndDispYnIsTrue(cateId).size() == 0)){
                    categoryRepository.deleteById(cateId);
                }
            }
            // 하위 카테고리 존재 하는 경우 미전시 처리
            category.setDispYn(false);
            categoryRepository.save(category);
        }
        return category;
    }

    /**
     * 대,중 분류 카테고리 조회 시 하위 카테고리조회
     * 소분류 카테고리 일때 해당 카테고리만 조회
     * @param cateId
     * @return
     */
    public Category searchCategoryById(long cateId) {
        return categoryRepository.findByIdAndDispYnIsTrue(cateId);
    }
    public List<Category> searchCategoryListById(long cateId) {
        List<Category> categoryList = new ArrayList<>();
        Category category = categoryRepository.findByIdAndDispYnIsTrue(cateId);

        if(category == null) return categoryList;

        if(category.getLevel() == 1){
            categoryList.addAll(categoryRepository.findAllByLargeCateIdAndDispYnIsTrue(cateId));
        }else if(category.getLevel() == 2){
            categoryList.addAll(categoryRepository.findAllByMediumCateIdAndDispYnIsTrue(cateId));
        }else{
            categoryList.add(category);
        }
        return categoryList;
    }

    /**
     * 카테고리 전체 조회
     * @return
     */
    public List<Category> searchCategoryAll() {
        List<Category> categoryList = categoryRepository.findAllByDispYnIsTrue();
        return categoryList;
    }

    public List<Category> searchCategoryAllRows() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
