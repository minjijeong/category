package com.api.category.service;

import com.api.category.model.dto.CategoryForm;
import com.api.category.model.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    CategoryService service;

    @PersistenceContext
    EntityManager em;

    @Transactional
    @BeforeEach
    void 카테고리기본생성() throws Exception{
        List<Category> categoryList = new ArrayList<>();
        Category cate = Category.builder()
                .cateName("소_소분류카테")
                .id(5L)
                .level(3)
                .largeCateId(1L)
                .mediumCateId(2L)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("대_대분류카테")
                .id(6L)
                .level(1)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("중_중분류카테")
                .id(7L)
                .level(2)
                .largeCateId(1L)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("소_소분류카테2")
                .id(8L)
                .largeCateId(1L)
                .mediumCateId(2L)
                .level(3)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("대_대분류카테")
                .id(9L)
                .level(1)
                .build();
        categoryList.add(cate);
        List<Category> result = service.saveAll(categoryList);
    }

    @Transactional
    @Test
    void 카테고리생성() throws Exception{
        CategoryForm cate = CategoryForm.builder()
                                        .cateName("대_대분류 카테 테스트")
                                        .level(1)
                                        .build();
        Category result = service.save(cate);
        Assertions.assertThat(cate.equals(result));
    }

    @Transactional
    @Test
    void 카테고리수정() {
        CategoryForm category = CategoryForm.builder()
                .cateName("카테고리 바꼈어여~~~ ")
                .id(10L)
                .level(3)
                .largeCateId(1L)
                .mediumCateId(2L)
                .build();
        Category result = service.update(category);
        Assertions.assertThat(category.getCateName()).isEqualTo(result.getCateName());
    }

    @Transactional
    @Test
    void 카테고리삭제(){
        long cateId = 9;
        service.delete(cateId);
        Category reseult = service.searchCategoryById(cateId);
        Assertions.assertThat(reseult).isNull();
    }

    @Test
    void 카테고리조회_단일조회(){
        long cateId = 3L;
        Category category = service.searchCategoryById(cateId);
        Assertions.assertThat(category.getId()).isEqualTo(cateId);
    }

    @Test
    void 카테고리조회_대분류기준(){
        long lCateId = 1;
        List<Category> TCategoryList = service.searchCategoryListById(lCateId);
        Assertions.assertThat(TCategoryList.size()).isEqualTo(3);
    }

    @Test
    void 카테고리조회_중분류기준(){
        long mCateId = 2;
        List<Category> TCategoryList = service.searchCategoryListById(mCateId);
        Assertions.assertThat(TCategoryList.size()).isEqualTo(2);
    }

    @AfterEach
    void clear() {
        em.clear();
    }
}
