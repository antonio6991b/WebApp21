package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.ProductCategory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
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

        Charset ch = Charset.forName("UTF-8");
        String content = "";
        try {
            content = new String(file.getBytes(), ch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


    public Map<String, String> fillProducts(MultipartFile file){
        String content = uploadFile(file);
        System.out.println(content);
        Set<String> hashSet = new HashSet<>();

        Map<String, String> map = new HashMap<>();
        try {
            map = Stream.of(content.split("\\;"))
                    .filter(x -> !hashSet.contains(x.split("\\=")[0]))
                    .map(x -> {hashSet.add(x.split("\\=")[0]); return x;})
                    .collect(Collectors.toMap(t -> t.split("\\=")[0], t -> t.split("\\=")[1]));
        }catch (Exception e){
            e.printStackTrace();
        }

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
                    Product tmp = productRepository.findByNameIgnoreCase(f.getKey());
                    if(Objects.isNull(tmp)) {
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
