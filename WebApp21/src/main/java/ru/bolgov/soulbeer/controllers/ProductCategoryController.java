package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.service.ProductCategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;



    @GetMapping("/all")
    public String allCategories(Model model){

        model.addAttribute("productCategories", productCategoryService.findAll());
        return "productCategories/all";
    }

    @GetMapping("/new")
    public String newCategory(Model model){
        model.addAttribute("productCategory", new ProductCategoryDto());
        return "productCategories/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("productCategory") ProductCategoryDto productCategoryDto) {
        productCategoryService.save(productCategoryDto);
        return "redirect:/product-category/all";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("productCategory", productCategoryService.findById(id));
        return "productCategories/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveCategory(@PathVariable("id") Long id, ProductCategoryDto productCategoryDto){
        productCategoryService.edit(productCategoryDto, id);
        return "redirect:/product-category/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("productCategory", productCategoryService.findById(id));
        return "productCategories/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteCategory(@PathVariable("id") Long id){
        productCategoryService.deleteById(id);
        return "redirect:/product-category/all";
    }
}
