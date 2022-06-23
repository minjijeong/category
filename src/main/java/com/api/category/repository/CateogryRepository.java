package com.api.category.repository;

import com.api.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateogryRepository extends JpaRepository<Category, String> {
}
