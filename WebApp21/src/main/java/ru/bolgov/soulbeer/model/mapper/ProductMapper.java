package ru.bolgov.soulbeer.model.mapper;

import ru.bolgov.soulbeer.model.dto.product.ProductDto;
import ru.bolgov.soulbeer.model.entity.Product;

public class ProductMapper {

    public ProductDto productToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName().trim());
        productDto.setCategoryId(product.getCategoryId());
        productDto.setProductMaker(product.getProductMaker());
        productDto.setProductOrder(product.getProductOrder());

        return productDto;
    }

    public Product dtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setCategoryId(productDto.getCategoryId());
        product.setProductMaker(productDto.getProductMaker());
        product.setProductOrder(productDto.getProductOrder());
        return product;
    }
}
