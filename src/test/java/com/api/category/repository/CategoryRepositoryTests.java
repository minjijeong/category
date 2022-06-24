package com.api.category.repository;

import com.api.category.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTests {

    @Autowired
    CategoryRepository repository;

    @PersistenceContext
    EntityManager em;

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
        List<Category> result = repository.saveAllAndFlush(categoryList);
    }

    @Test
    void 카테고리생성() throws Exception{
        Category cate = Category.builder()
                                .cateName("소_소분류카테")
                                .cateId("0000001")
                                .level(3)
                                .build();
        Category result = repository.save(cate);
        Assertions.assertThat(cate.equals(result));
    }

    @Test
    void 카테고리수정() {
        String cateId = "0000002";
        Category category = repository.findByCateId(cateId);
        category.setCateName("뉴_카테아이디");
        repository.save(category);
        Category result = repository.findByCateId(cateId);
        Assertions.assertThat(result.getCateName()).isEqualTo(category.getCateName());
    }

    @Test
    void 카테고리삭제(){
        String cateId = "0000002";
        repository.deleteById(cateId);
        Optional<Category> result = repository.findById(cateId);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void 카테고리조회_단일조회(){
        String cateId = "0000002";
        Optional<Category> category = repository.findById(cateId);
        Assertions.assertThat(category.get().getCateId()).isEqualTo(cateId);
    }

    @Test
    void 카테고리조회_대분류기준(){
        String lCateId = "0000002";
        List<Category> categoryList = repository.findAllByLCate(lCateId);
        Assertions.assertThat(categoryList.size()).isEqualTo(3);
    }

    @Test
    void 카테고리조회_중분류기준(){
        String mCateId = "0000003";
        List<Category> categoryList = repository.findAllByMCate(mCateId);
        Assertions.assertThat(categoryList.size()).isEqualTo(2);
    }

    @AfterEach
    void clear() {
        em.clear();
    }
}
