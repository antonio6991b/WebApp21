package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.report.ProductReport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String uploadFile(MultipartFile file){

        String content = "";
        try {
            content = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public Map<String, String> fillProducts(MultipartFile file){
        String content = uploadFile(file);

        Map<String, String> map = Stream.of(content.split("\\;"))
                .collect(Collectors.toMap(t -> t.toString().split("=")[0], t -> t.toString().split("=")[1]));
        System.out.println(content);
        System.out.println(Collections.singletonList(map));
        System.out.println(map.keySet());
        System.out.println(map.values());

        map.values().stream()
                .filter(f -> {
                   if(Objects.isNull(productCategoryRepository.findByName(f))) {
                       ProductCategory productCategory = new ProductCategory();
                       productCategory.setCategoryName(f);
                       productCategoryRepository.save(productCategory);
                   }
                   return false;
                })
                .count();

        map.entrySet().stream()
                .filter(f -> {
                    if(Objects.isNull(productRepository.findByName(f.getKey()))) {
                        Product product = new Product();
                        product.setProductName(f.getKey());
                        ProductCategory productCategory = productCategoryRepository.findByName(f.getValue());

                        product.setProductCategory(productCategory);
                        productRepository.save(product);
                    }
                    return false;
                })
                .count();

        return map;
    }
}
