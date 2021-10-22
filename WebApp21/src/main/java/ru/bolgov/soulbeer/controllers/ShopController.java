package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.shop.ShopDto;
import ru.bolgov.soulbeer.service.ShopService;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Controller
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    public String allShops(Model model){

        List<ShopDto> shops = new ArrayList<>();
        shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops/all";
    }

    @GetMapping("/new")
    public String newShop(Model model){
        model.addAttribute("shop", new ShopDto());
        return "shops/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("shop") ShopDto shop) {
        shopService.save(shop);
        return "redirect:/shops/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("shop", shopService.findById(id));

        return "shops/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("shop", shopService.findById(id));
        return "shops/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id){
        model.addAttribute("shop", shopService.findById(id));
        return "shops/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        shopService.deleteById(id);
        return "redirect:/shops/all";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("shop") ShopDto shopDto, @PathVariable("id") Long id) {
        shopService.edit(shopDto, id);
        return "redirect:/shops/all";
    }
}
