package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
//import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.dto.product.ProductDto;
import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.service.ProductCategoryService;
import ru.bolgov.soulbeer.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/all")
    public String allProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "products/all";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new ProductDto());

        List<ProductCategoryDto> productCategories = productCategoryService.findAll();
        model.addAttribute("productCategories", productCategories);
        return "products/new";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute("product") ProductDto productDto){
        productService.save(productDto);
        return "redirect:/products/all";
    }

    @GetMapping("/category/{id}")
    public String getProductsByCategory(@PathVariable("id") Long categoryId, Model model){

        model.addAttribute("products", productService.findByCategoryId(categoryId));
        return "products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "products/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){

        model.addAttribute("product", productService.findById(id));

        model.addAttribute("productCategories", productCategoryService.findAll());
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveProduct(@PathVariable("id") Long id, ProductDto productDto){
        productService.edit(productDto, id);
        return "redirect:/products/all";
    }
}
