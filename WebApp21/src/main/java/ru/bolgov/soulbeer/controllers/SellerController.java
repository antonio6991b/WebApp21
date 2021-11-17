package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.dto.shop.ShopDto;
import ru.bolgov.soulbeer.model.entity.Shop;
import ru.bolgov.soulbeer.service.SellerService;
import ru.bolgov.soulbeer.service.ShopService;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    public String allSellers(Model model){

        model.addAttribute("sellers", sellerService.findAll());
        return "sellers/all";
    }

    @GetMapping("/new")
    public String newShop(Model model){
        model.addAttribute("seller", new SellerDto());
        model.addAttribute("shops", shopService.findAll());
        return "sellers/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("seller") SellerDto seller) {
        sellerService.save(seller);
        return "redirect:/sellers/all";
    }

    @GetMapping("/shop/{id}")
    public String getSellersByShop(@PathVariable("id") Long shopId, Model model){

        List<SellerDto> sellers = sellerService.findByShopId(shopId);

        model.addAttribute("sellers", sellers);
        return "sellers/all";
    }

    @GetMapping("/{id}")
    public String showSeller(@PathVariable("id") Long id, Model model){
        model.addAttribute("seller", sellerService.findById(id));
        model.addAttribute("otherShops", sellerService.findOtherShops(id));
        model.addAttribute("shops", sellerService.findMyShops(id));
        model.addAttribute("shop", new Shop());
        return "sellers/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("seller", sellerService.findById(id));
        List<ShopDto> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "sellers/edit";
    }

    @PostMapping("/add-shop/{sellerId}")
    public String addShop(@PathVariable("sellerId") Long sellerId,
                          @ModelAttribute Shop shop) {

        sellerService.addSellerToShop(sellerId, shop.getShopId());
        return "redirect:/sellers/" + sellerId;
    }

    @PostMapping("/delete-shop/{sellerId}")
    public String deleteShop(@PathVariable("sellerId") Long sellerId,
                          @ModelAttribute Shop shop) {

        sellerService.removeSellerFromShop(sellerId, shop.getShopId());
        return "redirect:/sellers/" + sellerId;
    }

    @PostMapping("/{id}")
    public String editSeller(@ModelAttribute("seller") SellerDto seller, @PathVariable("id") Long id){
        sellerService.edit(seller, id);
        return "redirect:/sellers/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeller(@PathVariable("id") Long id, Model model){
        model.addAttribute("seller", sellerService.findById(id));
        return "sellers/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long id){
        sellerService.deleteById(id);
        return "redirect:/sellers/all";
    }

}
