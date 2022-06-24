package com.api.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {
    // 현재 카테고리 ID
    @Id
    private String cateId;

    // 카테고리 레벨
    @Column(nullable = false)
    private int level;

    // 카테고리명
    @Column(nullable = false)
    private String cateName;

    // 대분류 카테고리 코드
    private String largeCateId;

    // 중분류 카테고리 코드
    private String mediumCateId;

    // 소분류 카테고리 코드
    private String smallCateId;

    // 카테고리 노출 여부
    private boolean dispYn;

    @Builder
    public Category(String cateId, int level, String cateName, String largeCateId, String mediumCateId,
                    String smallCateId) {
        this.cateId = cateId;
        this.level = level;
        this.cateName = cateName;
        this.largeCateId = largeCateId;
        this.mediumCateId = mediumCateId;
        this.smallCateId = smallCateId;
    }

    public Category() {}
    @Override
    public String toString() {
        return "Category{" +
                "cateId='" + cateId + '\'' +
                ", level=" + level +
                ", cateName='" + cateName + '\'' +
                ", largeCateId='" + largeCateId + '\'' +
                ", mediumCateId='" + mediumCateId + '\'' +
                ", smallCateId='" + smallCateId + '\'' +
                '}';
    }

}
