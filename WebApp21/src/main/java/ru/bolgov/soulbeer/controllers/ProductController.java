package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.Seller;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @GetMapping("/all")
    public String allProducts(Model model){
        List<Product> products = new ArrayList<>();
        try {
            products = productRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("products", products);
        return "products/all";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());

        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            productCategories = productCategoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("productCategories", productCategories);
        return "products/new";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute("product") Product product){
        productRepository.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/category/{id}")
    public String getProductsByCategory(@PathVariable("id") Long categoryId, Model model){

        List<Product> products = new ArrayList<>();
        try {
            products = productRepository.findByCategoryId(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("products", products);
        return "products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute(productRepository.findById(id).orElse(new Product()));
        return "products/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){

        model.addAttribute("product", productRepository.findById(id).orElse(new Product()));

        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            productCategories = productCategoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("productCategories", productCategories);
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveProduct(@PathVariable("id") Long id, Product product){
        productRepository.edit(product, id);
        return "redirect:/products/all";
    }
}
