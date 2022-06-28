package com.api.category.model.criteria;

import lombok.Data;

@Data
public class CategoryCriteria {
    // 카테고리 ID
    private Long id;

    // 카테고리 레벨
    private int level;

    public CategoryCriteria(Long id, int level) {
        this.id = id;
        this.level = level;
    }
}
