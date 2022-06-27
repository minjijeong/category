package com.api.category.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {
    private Long id;

    // 카테고리 레벨
    private int level;

    // 카테고리명
    private String cateName;

    // 대분류 카테고리 코드
    private Long largeCateId;

    // 중분류 카테고리 코드
    private Long mediumCateId;

    // 소분류 카테고리 코드
    private Long smallCateId;

    // 카테고리 노출 여부
    private boolean dispYn;

    @Builder
    public CategoryForm(long id, int level, String cateName, long largeCateId, long mediumCateId,
                    long smallCateId, boolean dispYn) {
        this.id = id;
        this.level = level;
        this.cateName = cateName;
        this.largeCateId = largeCateId;
        this.mediumCateId = mediumCateId;
        this.smallCateId = smallCateId;
        this.dispYn = dispYn;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId='" + id + '\'' +
                ", level=" + level +
                ", cateName='" + cateName + '\'' +
                ", largeCateId='" + largeCateId + '\'' +
                ", mediumCateId='" + mediumCateId + '\'' +
                ", smallCateId='" + smallCateId + '\'' +
                '}';
    }
}
