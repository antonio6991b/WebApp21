package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public ProductCategory(){}

    public ProductCategory(String categoryName){
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
