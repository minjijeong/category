package com.api.category.repository;

import com.api.category.model.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("Select c from Category c where c.id = :id")
    Category findByCateId(Long id);

    @Query("Select c from Category c where c.largeCateId = :lCateId")
    List<Category> findAllByLCate(Long lCateId);

    @Query("Select c from Category c where c.mediumCateId = :mCateId")
    List<Category> findAllByMCate(Long mCateId);

    @Query("select max(c.id) from Category c")
    Long getMaxCateId();

//    @Modifying
//    @Query("update from Category where c.id = :category.getId()")
//    Category update(Category category);

//    @Query("Select c from Category c where c.smallCateId = :sCateId")
//    List<Category> findAllBySCate(String sCateId);
}