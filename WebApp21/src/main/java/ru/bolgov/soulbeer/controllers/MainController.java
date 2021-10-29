package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import ru.bolgov.soulbeer.dao.ProductCategoryRepository;
//import ru.bolgov.soulbeer.dao.ShopRepository;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.model.entity.Shop;
import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.service.FileService;
import ru.bolgov.soulbeer.service.TestDataService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@EnableWebMvc
public class MainController {

    @Autowired
    private FileService fileService;

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
        return "redirect:/main";
    }

//    @GetMapping("/test")
//    public String fillTestData(){
//        LocalDate localDate = LocalDate.of(2021,01,01);
//
//        for (int i = 0; i < 10; i ++){
//            Shop shop = testDataService.createShop();
//            Seller seller = testDataService.createSeller(shop);
//        }
//
//        List<Shop> shops = shopRepository.findAll();
//        int j = 0;
//        for(int i = 0; i < 2; i ++){
//
//            for(Shop shop : shops){
//                Shift shift = testDataService.createShift(shop, localDate);
//                testDataService.createReport(shift);
//                j++;
//                System.out.println("create report #" + j);
//
//            }
//
//
//            localDate = localDate.plusDays(7);
//        }
//
//        return "index";
//    }

    @GetMapping("/product-list")
    public String getProductList(Model model){
        String products = fileService.getProductList();
        model.addAttribute("products", products);

        return "fill/productList";
    }
}
