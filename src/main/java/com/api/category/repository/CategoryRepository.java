package com.api.category.repository;

import com.api.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {

    Category findByCateId(String id);

    @Query("Select c from Category c where c.largeCateId = :lCateId")
    List<Category> findAllByLCate(String lCateId);

    @Query("Select c from Category c where c.mediumCateId = :mCateId")
    List<Category> findAllByMCate(String mCateId);

//    @Modifying
//    @Query("update from Category where c.id = :category.getId()")
//    Category update(Category category);

//    @Query("Select c from Category c where c.smallCateId = :sCateId")
//    List<Category> findAllBySCate(String sCateId);
}