package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
import ru.bolgov.soulbeer.service.FileService;

import java.io.*;
import java.nio.file.*;


@Controller
@EnableWebMvc
public class MainController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    FileService fileService;

    @GetMapping("/main")
    public String main(){
        return "index";
    }


    @GetMapping("fill-products")
    public String fillProduct(Model model){


        return "fill/fillProduct";
    }

    @PostMapping("fill-products")
    public String fill(@RequestParam("file") MultipartFile file){



            fileService.fillProducts(file);





//
        return "redirect:/main";
    }
}
