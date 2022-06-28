package com.api.category.repository;

import com.api.category.model.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리 ID 기준 카테고리 조회 (전시여부 : 전시)
     * @param id
     * @return
     */
    Category findByIdAndDispYnIsTrue(Long id);

    /**
     * 대분류 카테고리 ID 기준 하위 카테고리 조회 (전시여부 : 전시)
     * @param lcateId
     * @return
     */
    List<Category> findAllByLargeCateIdAndDispYnIsTrue(Long lcateId);

    /**
     * 중분류 카테고리 ID 기준 하위 카테고리 조회 (전시여부 : 전시)
     * @param mCateId
     * @return
     */
    List<Category> findAllByMediumCateIdAndDispYnIsTrue(Long mCateId);

    /**
     * 카테고리 ID 채번
     * @return
     */
    @Query("select max(c.id) from Category c")
    Long getMaxCateId();

    /**
     * 카테고리 전체 조회 (전시여부 : 전시)
     * @return
     */
    List<Category> findAllByDispYnIsTrue();

    /**
     * 카테고리명 중복 조회
     * @param cateName
     * @return
     */
    Category findByCateName(String cateName);

    /**
     * 특정 레벨, 카테고리 존재 유무 조회
     * @param id
     * @param level
     * @return
     */
    Category findByIdAndLevel(Long id, int level);
}
