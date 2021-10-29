package ru.bolgov.soulbeer.model.mapper;

import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.entity.ProductCategory;

public class ProductCategoryMapper {

    public ProductCategoryDto entityToDto(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setCategoryId(productCategory.getCategoryId());
        productCategoryDto.setCategoryName(productCategory.getCategoryName().trim());
        return productCategoryDto;
    }

    public ProductCategory dtoToEntity(ProductCategoryDto productCategoryDto){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(productCategoryDto.getCategoryId());
        productCategory.setCategoryName(productCategoryDto.getCategoryName());
        return productCategory;
    }
}
