package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
//import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.dto.product.ProductDto;
import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.repository.ProductCategoryRepository;
import ru.bolgov.soulbeer.repository.ProductRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String uploadFile(MultipartFile file){

        Charset ch = Charset.forName("UTF-8");
        String content = "";
        try {
            content = new String(file.getBytes(), ch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


    public void fillProducts(MultipartFile file){
        String content = uploadFile(file);

        List<Long> categories = new ArrayList<>();

        long count = Arrays.stream(content.split("\\)"))
                .map(x -> {
                    String categoryName = x.split("\\(")[0].trim();
                    String repoCategoryName = productCategoryRepository.findByName(categoryName).getCategoryName();
                    if(!productCategoryRepository.existsByCategoryName(categoryName)){
                        ProductCategory productCategory = new ProductCategory();
                        productCategory.setCategoryName(categoryName);
                        productCategoryRepository.save(productCategory);
                    }
                    categories.add(productCategoryRepository.findByName(categoryName).getCategoryId());

                    long prod = Arrays.stream(x.split("\\(")[1].split("\\,"))
                            .filter(pro -> {
                                String productName = x.trim();
                                if(productRepository.findByProductNameAndCategoryId(x, categories.get(categories.size()-1)).size()==0){
                                    Product product = new Product();
                                    product.setCategoryId(categories.get(categories.size()-1));
                                    product.setProductName(pro);
                                    productRepository.save(product);
                                }
                                return true;
                            })
                            .count();
                    return prod;
                })
                .count();
    }

    public String getProductList(){
        List<ProductCategory> categories = new ArrayList<>();
        try {
            categories = productCategoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        StringBuilder products = new StringBuilder();
        for(ProductCategory category : categories){
            products.append(category.getCategoryName() + "(");
            long count = productRepository.countByCategoryId(category.getCategoryId());
            for(Product product: productRepository.findByCategoryId(category.getCategoryId())){
                products.append(product.getProductName());
                count--;
                if(count > 0){
                    products.append(",");
                }
            }
            products.append(")");
        }
        if(products.toString().equals("")){
            products.append("Список товаров пуст");
        }
        return products.toString();
    }
}
