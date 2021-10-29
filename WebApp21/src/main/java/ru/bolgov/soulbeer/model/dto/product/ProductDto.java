package ru.bolgov.soulbeer.model.dto.product;

public class ProductDto {

    private Long productId;

    private String productName;

    private Long categoryId;

    private String categoryName;

    private String productMaker;

    private int productOrder;

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
}
