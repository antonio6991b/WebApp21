package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.SellerRepository;
import ru.bolgov.soulbeer.dao.ShopRepository;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.Shop;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/all")
    public String allSellers(Model model){
        List<Seller> sellers = new ArrayList<>();

        try {
            sellers = sellerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sellers", sellers);
        return "sellers/all";
    }

    @GetMapping("/new")
    public String newShop(Model model){
        model.addAttribute("seller", new Seller());

        List<Shop> shops = new ArrayList<>();
        try {
            shops = shopRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("shops", shops);
        return "sellers/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("seller") Seller seller) {
        sellerRepository.save(seller);
        return "redirect:/sellers/all";
    }

    @GetMapping("/shop/{id}")
    public String getSellersByShop(@PathVariable("id") Long shopId, Model model){

        List<Seller> sellers = new ArrayList<>();
        try {
            sellers = sellerRepository.findByShopId(shopId);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sellers", sellers);
        return "sellers/all";
    }

    @GetMapping("/{id}")
    public String showSeller(@PathVariable("id") Long id, Model model){
        model.addAttribute("seller", sellerRepository.findById(id).orElse(new Seller()));
        return "sellers/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("seller", sellerRepository.findById(id).orElse(new Seller()));
        List<Shop> shops = new ArrayList<>();
        try {
            shops = shopRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("shops", shops);
        return "sellers/edit";
    }

    @PostMapping("/{id}")
    public String editSeller(@ModelAttribute("seller") Seller seller, @PathVariable("id") Long id){
        sellerRepository.edit(seller, id);
        return "redirect:/sellers/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeller(@PathVariable("id") Long id, Model model){
        model.addAttribute("seller", sellerRepository.findById(id).orElse(new Seller()));
        return "sellers/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long id){
        try {
            sellerRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/sellers/all";
    }

}
