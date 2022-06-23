package com.api.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.GeneratorType;

@Getter
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
    private String LargeCateId;

    // 중분류 카테고리 코드
    private String MediumCateId;

    // 소분류 카테고리 코드
    private String SmallCateId;
}
