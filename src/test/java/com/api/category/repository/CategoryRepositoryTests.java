package com.api.category.repository;

import com.api.category.model.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
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
                .id(5L)
                .cateName("소_소분류카테")
                .level(3)
                .largeCateId(1L)
                .mediumCateId(2L)
                .dispYn(true)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("대_대분류카테")
                .id(6L)
                .level(1)
                .dispYn(true)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("중_중분류카테")
                .id(7L)
                .level(2)
                .largeCateId(1L)
                .dispYn(true)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("소_소분류카테2")
                .id(8L)
                .largeCateId(1L)
                .mediumCateId(2L)
                .level(3)
                .dispYn(true)
                .build();
        categoryList.add(cate);
        cate = Category.builder()
                .cateName("대_대분류카테")
                .id(9L)
                .level(1)
                .dispYn(true)
                .build();
        categoryList.add(cate);
        List<Category> result = repository.saveAll(categoryList);
        clear();
    }

    @Test
    void 카테고리생성() throws Exception{
        Category cate = Category.builder()
                                .id(10L)
                                .cateName("대분류카테_단일생성")
                                .level(1)
                                .dispYn(true)
                                .build();
        Category result = repository.save(cate);
        clear();

        Assertions.assertThat(cate.equals(result));
    }

    @Test
    void 카테고리수정() {
        long cateId = 2L;
        Category category = repository.findByIdAndDispYnIsTrue(cateId);
        category.setCateName("뉴_카테아이디");
        repository.save(category);
        clear();

        Category result = repository.findByIdAndDispYnIsTrue(cateId);
        Assertions.assertThat(result.getCateName()).isEqualTo(category.getCateName());
    }

    @Test
    void 카테고리삭제(){
        long cateId = 9;
        Category result = repository.findByIdAndDispYnIsTrue(cateId);
        repository.deleteById(result.getId());
        clear();

        result = repository.findByIdAndDispYnIsTrue(cateId);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void 카테고리조회_단일조회(){
        long cateId = 3;
        Category category = repository.findByIdAndDispYnIsTrue(cateId);
        Assertions.assertThat(category.getId()).isEqualTo(cateId);
    }

    @Test
    void 카테고리조회_대분류기준(){
        long lCateId = 1L;
        List<Category> CategoryList = repository.findAllByLargeCateIdAndDispYnIsTrue(lCateId);
        Assertions.assertThat(CategoryList.size()).isEqualTo(6);
    }

    @Test
    void 카테고리조회_중분류기준(){
        long mCateId = 2L;
        List<Category> CategoryList = repository.findAllByMediumCateIdAndDispYnIsTrue(mCateId);
        Assertions.assertThat(CategoryList.size()).isEqualTo(4);
    }

    @Test
    void 카테고리조회_전체조회(){
        List<Category> category = repository.findAllByDispYnIsTrue();
        Assertions.assertThat(category).isNotNull();
    }


    void clear() {
//        em.flush();
        em.clear();
    }
}
