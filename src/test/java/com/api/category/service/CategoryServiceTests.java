package com.api.category.service;

import com.api.category.entity.Category;
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
                .cateId("0000005")
                .level(3)
                .largeCateId("0000002")
                .mediumCateId("0000003")
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("대_대분류카테")
                .cateId("0000002")
                .level(1)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("중_중분류카테")
                .cateId("0000003")
                .level(2)
                .largeCateId("0000002")
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("소_소분류카테2")
                .cateId("0000004")
                .largeCateId("0000002")
                .mediumCateId("0000003")
                .level(3)
                .build();
        categoryList.add(cate);
        List<Category> result = service.saveAll(categoryList);
    }

    @Transactional
    @Test
    void 카테고리생성() throws Exception{
        Category cate = Category.builder()
                .cateName("소_소분류카테")
                .cateId("0000001")
                .level(3)
                .build();
        Category result = service.save(cate);
        Assertions.assertThat(cate.equals(result));
    }

    @Transactional
    @Test
    void 카테고리수정() {
        Category category = Category.builder()
                .cateName("카테고리 바꼈어여~~~ ")
                .cateId("0000005")
                .level(3)
                .largeCateId("0000002")
                .mediumCateId("0000003")
                .build();
        Category result = service.update(category);
        Assertions.assertThat(result.getCateName()).isEqualTo(category.getCateName());
    }

    @Transactional
    @Test
    void 카테고리삭제(){
        String cateId = "0000002";
        service.delete(cateId);
        Category reseult = service.searchCategoryById(cateId);
        Assertions.assertThat(reseult).isNull();
    }

    @Test
    void 카테고리조회_단일조회(){
        String cateId = "0000002";
        Category category = service.searchCategoryById(cateId);
        Assertions.assertThat(category.getCateId()).isEqualTo(cateId);
    }

    @Test
    void 카테고리조회_대분류기준(){
        String lCateId = "0000002";
        List<Category> categoryList = service.searchCategoryByLCateId(lCateId);
        Assertions.assertThat(categoryList.size()).isEqualTo(3);
    }

    @Test
    void 카테고리조회_중분류기준(){
        String mCateId = "0000003";
        List<Category> categoryList = service.searchCategoryByMCateId(mCateId);
        Assertions.assertThat(categoryList.size()).isEqualTo(2);
    }

    @AfterEach
    void clear() {
        em.clear();
    }
}
