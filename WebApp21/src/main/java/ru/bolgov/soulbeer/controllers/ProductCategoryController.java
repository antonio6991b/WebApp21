package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.Shop;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;



    @GetMapping("/all")
    public String allCategories(Model model){
        List<ProductCategory> productCategories = new ArrayList<>();

        try {
            productCategories = productCategoryRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("productCategories", productCategories);
        return "productCategories/all";
    }

    @GetMapping("/new")
    public String newCategory(Model model){
        model.addAttribute("productCategory", new ProductCategory());
        return "productCategories/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("productCategory") ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
        return "redirect:/product-category/all";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute(productCategoryRepository.findById(id).orElse(new ProductCategory()));
        return "productCategories/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveCategory(@PathVariable("id") Long id, ProductCategory productCategory){
        productCategoryRepository.edit(productCategory, id);
        return "redirect:/product-category/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute(productCategoryRepository.findById(id).orElse(new ProductCategory()));
        return "productCategories/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteCategory(@PathVariable("id") Long id){
        productCategoryRepository.deleteById(id);
        return "redirect:/product-category/all";
    }
}
