package ru.bolgov.soulbeer.service;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.product.ProductDto;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.model.mapper.ProductMapper;
import ru.bolgov.soulbeer.repository.ProductCategoryRepository;
import ru.bolgov.soulbeer.repository.ProductRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductMapper productMapper = new ProductMapper();

    public void edit(ProductDto productDto, Long id){
        Product product = productMapper.dtoToProduct(productDto);
        Product tmp = productRepository.findById(id).orElse(new Product());
        if(Objects.nonNull(tmp)){
            tmp.setProductName(product.getProductName().trim());
            tmp.setCategoryId(product.getCategoryId());
            tmp.setProductMaker(product.getProductMaker());
            tmp.setProductOrder(product.getProductOrder());
            productRepository.save(tmp);
        }
    }

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream()
                .map(x -> {
                    ProductDto productDto = productMapper.productToDto(x);
                    productDto.setCategoryName(productCategoryRepository.findById(productDto.getCategoryId()).orElse(new ProductCategory()).getCategoryName());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    public void save(ProductDto productDto){
        boolean isPresent = productRepository.findByCategoryId(productDto.getCategoryId()).stream()
                .map(x -> x.getProductName().trim().toLowerCase(Locale.ROOT))
                .anyMatch(x -> (x.equalsIgnoreCase(productDto.getProductName().trim().toLowerCase(Locale.ROOT))));

        if (!isPresent){
            productRepository.save(productMapper.dtoToProduct(productDto));
        }
    }

    public List<ProductDto> findByCategoryId(Long categoryId){
        return productRepository.findByCategoryId(categoryId).stream()
                .map(x -> {
                    ProductDto productDto = productMapper.productToDto(x);
                    productDto.setCategoryName(productCategoryRepository.findById(productDto.getCategoryId()).orElse(new ProductCategory()).getCategoryName());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public ProductDto findById(Long id){
        ProductDto productDto = productMapper.productToDto(productRepository.findById(id).orElse(new Product()));
        productDto.setCategoryName(productCategoryRepository.findById(productDto.getCategoryId()).orElse(new ProductCategory()).getCategoryName());
        return productDto;
    }
}
