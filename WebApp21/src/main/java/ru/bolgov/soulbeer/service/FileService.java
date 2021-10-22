package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
//import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductCategory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {

//    @Autowired
//    ProductCategoryRepository productCategoryRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Value("${app.upload.dir:${user.home}}")
//    public String uploadDir;
//
//    public String uploadFile(MultipartFile file){
//
//        Charset ch = Charset.forName("UTF-8");
//        String content = "";
//        try {
//            content = new String(file.getBytes(), ch);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return content;
//    }
//
//
//    public void fillProducts(MultipartFile file){
//        String content = uploadFile(file);
//
//        Set<ProductCategory> categorySet =
//                productCategoryRepository.findAll()
//                                .stream()
//                                .collect(Collectors.toSet());
//        Set<Product> products = productRepository.findAll().stream()
//                .collect(Collectors.toSet());
//
//        long count = Arrays.stream(content.split("\\)"))
//                .map(x -> {
//                    String tmpCategoryName = x.split("\\(")[0];
//                    ProductCategory tmp = categorySet.stream()
//                            .filter(category -> {
//                                return category.getCategoryName().equalsIgnoreCase(tmpCategoryName);
//                            }).findAny().orElse(null);
//                    if(Objects.isNull(tmp)){
//                        productCategoryRepository.save(new ProductCategory(tmpCategoryName));
//                    }
//
//                    long prod = Arrays.stream(x.split("\\(")[1].split("\\,"))
//                            .filter(pro -> {
//                                Product product = new Product();
//                                product.setProductCategory(productCategoryRepository.findByName(tmpCategoryName));
//                                product.setProductName(pro);
//                                if(!products.contains(product)){
//                                    productRepository.save(product);
//                                }
//                                return false;
//                            })
//                            .count();
//                    return prod;
//                })
//                .count();
//    }
}
