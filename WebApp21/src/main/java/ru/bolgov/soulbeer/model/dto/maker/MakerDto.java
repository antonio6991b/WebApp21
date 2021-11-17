package ru.bolgov.soulbeer.model.dto.maker;

import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductMaker;

import java.util.List;

public class MakerDto {
    private ProductMaker maker;
    private List<Product> products;
    private Long countProduct;

    public ProductMaker getMaker() {
        return maker;
    }

    public void setMaker(ProductMaker maker) {
        this.maker = maker;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Long countProduct) {
        this.countProduct = countProduct;
    }
}
