package ru.bolgov.soulbeer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.dto.util.DateInterval;
import ru.bolgov.soulbeer.model.dto.util.DateTemplate;
import ru.bolgov.soulbeer.model.entity.Seller;
import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.repository.SellerRepository;
import ru.bolgov.soulbeer.service.SellerService;
import ru.bolgov.soulbeer.service.ShiftService;
import ru.bolgov.soulbeer.service.ShopService;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Controller
@EnableWebMvc
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ShopService shopService;

    private DateTemplate dateTemplate = new DateTemplate();

    private Date from = Date.valueOf("2021-01-01");
    private Date to = Date.valueOf("2021-01-02");

    private DateInterval dateInterval = new DateInterval();


    @GetMapping("/all")
    public String allShifts(Model model){
        List<ShiftTemplate> shifts = shiftService.findAll();
        model.addAttribute("shifts", shifts);
        return "shifts/all";
    }

    @GetMapping("/filter")
    public String filterShifts(Model model){
        this.from = dateInterval.getFrom().createDate();
        this.to = dateInterval.getTo().createDate();
        List<ShiftTemplate> shifts = shiftService.findByInterval(from, to);
        model.addAttribute("shifts", shifts);
        return "shifts/all";
    }

    @GetMapping("/new")
    public String createShift(Model model){
        model.addAttribute("shops", shopService.findAll());
        return "shifts/selectShop";
    }


    @GetMapping("/new/{shopId}")
    public String newShift(@PathVariable("shopId") Long shopId, Model model){
        ShiftTemplate shiftTemplate = new ShiftTemplate();
        Shift shift = new Shift();
        shift.setShopId(shopId);
        Shift lastShift = shiftService.getLastShift(shopId);
        if(Objects.nonNull(lastShift)){
            shift.setCashBegin(lastShift.getCashEnd());
        }

        shiftTemplate.setShift(shift);

        model.addAttribute("shiftTemplate", shiftTemplate);

        List<SellerDto> sellers = sellerService.findByShopId(shopId);

        model.addAttribute("sellers", sellers);
        model.addAttribute("dateTemplate", dateTemplate);

        return "shifts/new";
    }

    @PostMapping("/")
    public String createShift(@ModelAttribute("shiftTemplate") ShiftTemplate shiftTemplate){
        System.out.println(shiftTemplate.getShopId());
        shiftService.save(shiftTemplate);
        return "redirect:/shifts/all";
    }

    @GetMapping("/edit/{id}")
    public String editShift(@PathVariable("id") Long id, Model model){
        ShiftTemplate shiftTemplate = shiftService.findById(id);
        model.addAttribute("shiftTemplate", shiftTemplate);
        List<SellerDto> sellers = sellerService.findByShopId(shiftTemplate.getShift().getShopId());

        model.addAttribute("sellers", sellers);
        model.addAttribute("dateTemplate", dateTemplate);
        return "shifts/edit";
    }

    @PostMapping("/edit/{id}")
    public String confirmEdit(@PathVariable("id") Long id, @ModelAttribute ShiftTemplate shiftTemplate){
        shiftService.edit(shiftTemplate.createShift(), id);
        return "redirect:/shifts/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteShift(@PathVariable("id") Long id, Model model){
        model.addAttribute("shift", shiftService.findById(id));
        return "shifts/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long id){
        shiftService.delete(id);
        return "redirect:/shifts/all";
    }
}
