package ru.bolgov.soulbeer.model;

import ru.bolgov.soulbeer.model.report.ProductReport;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryId")
    private ProductCategory productCategory;

    @Column(name = "productMaker")
    private String productMaker;

    @Column(name = "productOrder")
    private int productOrder;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductReport> reports;

    public Product(){}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }


    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductMaker() {
        return productMaker;
    }

    public void setProductMaker(String productMaker) {
        this.productMaker = productMaker;
    }

    public int getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(int productOrder) {
        this.productOrder = productOrder;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productMaker='" + productMaker + '\'' +
                ", productOrder=" + productOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) && Objects.equals(productCategory, product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productCategory);
    }
}
