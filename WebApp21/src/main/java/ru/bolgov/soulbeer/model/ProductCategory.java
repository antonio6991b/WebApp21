package ru.bolgov.soulbeer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productCategories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public ProductCategory(){}

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
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
