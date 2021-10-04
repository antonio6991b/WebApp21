package ru.bolgov.soulbeer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.ShopRepository;
import ru.bolgov.soulbeer.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@EnableWebMvc
@Controller
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/all")
    public String allShops(Model model){

        List<Shop> shops = new ArrayList<>();
        try {
            shops = shopRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("shops", shops);
        return "shops/all";
    }

    @GetMapping("/new")
    public String newShop(Model model){
        model.addAttribute("shop", new Shop());
        return "shops/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("shop") Shop shop) {
        shopRepository.save(shop);
        return "redirect:/shops/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("shop", shopRepository.findById(id).orElse(new Shop()));

        return "shops/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("shop", shopRepository.findById(id).orElse(new Shop()));
        return "shops/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("shop") Shop shop, @PathVariable("id") Long id) {
        Shop tmpShop = shopRepository.findById(id).orElse(null);
        if(Objects.nonNull(tmpShop)){
            tmpShop.setShopName(shop.getShopName());
            tmpShop.setShopAddress(shop.getShopAddress());
            tmpShop.setShopPhone(shop.getShopPhone());
            tmpShop.setIsCity(shop.getIsCity());
            shopRepository.save(tmpShop);
        }

        return "redirect:/shops/all";
    }
}
