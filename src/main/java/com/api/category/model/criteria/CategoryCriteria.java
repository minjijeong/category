package com.api.category.model.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCriteria {
    // 카테고리 ID
    private Long id;

    public CategoryCriteria(Long id) {
        this.id = id;
    }
}
