package com.api.category.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    private Long id;

    // 카테고리 레벨
    @Column(nullable = false)
    private int level;

    // 카테고리명
    @Column(nullable = false)
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
    public Category(Long id, int level, String cateName, Long largeCateId, Long mediumCateId,
                     long smallCateId, boolean dispYn) {
        this.id = id;
        this.level = level;
        this.cateName = cateName;
        this.largeCateId = largeCateId;
        this.mediumCateId = mediumCateId;
        this.smallCateId = smallCateId;
        this.dispYn = dispYn;
    }

    public Category() {}
}
