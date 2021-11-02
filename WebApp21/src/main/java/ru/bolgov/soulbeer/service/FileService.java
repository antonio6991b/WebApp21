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
import java.util.stream.Stream;

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
        System.out.println("start fill products");
        long count = Stream.of(content.split("\\)"))
                        .map(x -> {
                            String contentCategoryName = x.split("\\(")[0];
                            List<ProductCategory> productCategoriesInRepo =
                                    productCategoryRepository.findAll();
                            ProductCategory productCategory = productCategoriesInRepo.stream()
                                    .filter(nn-> nn.getCategoryName().trim().equalsIgnoreCase(contentCategoryName.trim()))
                                    .findAny().orElse(null);
                            if(Objects.isNull(productCategory)){
                                productCategory = new ProductCategory();
                                productCategory.setCategoryName(contentCategoryName.trim());
                                productCategoryRepository.save(productCategory);
                            }

                            long productCategoryId = productCategoryRepository.findByName(contentCategoryName.trim()).getCategoryId();

                            long z = Stream.of(x.split("\\(")[1].split("\\,"))
                                    .map(y -> {
                                        List<Product> productsInCategory = productRepository.findByCategoryId(productCategoryId);
                                        Product inRepo = productsInCategory.stream()
                                                .filter(e -> {
                                                    if (e.getProductName().trim()
                                                            .equalsIgnoreCase(y.trim())){
                                                        return true;
                                                    }
                                                    return false;
                                                })
                                                .findAny().orElse(null);
                                        if(Objects.isNull(inRepo)){
                                            Product productToSave = new Product();
                                            productToSave.setCategoryId(productCategoryId);
                                            productToSave.setProductName(y.trim());
                                            productRepository.save(productToSave);
                                        }

                                        return 5;
                                    })
                                    .count();

                            return 5;
                        })
                .count();

        System.out.println("end fill products");
    }

    public List<String> getProductList(){
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
                    products.append(", ");
                }
            }
            products.append(")" + "\n");
        }
        if(products.toString().equals("")){
            products.append("Список товаров пуст");
        }
        System.out.println(products.toString());
        return Stream.of(products.toString().split("\n"))
                .collect(Collectors.toList());
    }
}
