package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.model.mapper.ProductCategoryMapper;
import ru.bolgov.soulbeer.repository.ProductCategoryRepository;
import ru.bolgov.soulbeer.repository.ProductRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();

    public void edit(ProductCategoryDto productCategoryDto, Long id){
        ProductCategory tmp = productCategoryRepository.findById(id).orElse(new ProductCategory());
        tmp.setCategoryName(productCategoryDto.getCategoryName().trim());
        productCategoryRepository.save(tmp);
    }

    public List<ProductCategoryDto> findAll(){
        return productCategoryRepository.findAll().stream()
                .map(x -> {
                    ProductCategoryDto productCategoryDto = productCategoryMapper.entityToDto(x);
                    productCategoryDto.setProductCount(productRepository.countByCategoryId(x.getCategoryId()));
                    return productCategoryDto;
                })
                .collect(Collectors.toList());
    }

    public ProductCategoryDto findById(Long categoryId){
        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElse(new ProductCategory());
        ProductCategoryDto productCategoryDto = productCategoryMapper.entityToDto(productCategory);
        productCategoryDto.setProductCount(productRepository.countByCategoryId(categoryId));
        return productCategoryDto;
    }

    public void save(ProductCategoryDto productCategoryDto){
        boolean isPresent = productCategoryRepository.findAll().stream()
                        .map(x -> x.getCategoryName().trim().toLowerCase(Locale.ROOT))
                        .anyMatch(x -> x.equalsIgnoreCase(productCategoryDto.getCategoryName().trim().toLowerCase(Locale.ROOT)));

        if (!isPresent){
            productCategoryRepository.save(productCategoryMapper.dtoToEntity(productCategoryDto));
        }

    }

    public void deleteById(Long id){
        productCategoryRepository.deleteById(id);
    }

    public Long getCategoryIdByName(String categoryName){
        return productCategoryRepository.findByName(categoryName).getCategoryId();
    }
}
