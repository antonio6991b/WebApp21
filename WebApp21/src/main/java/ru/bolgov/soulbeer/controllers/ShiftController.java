package ru.bolgov.soulbeer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.SellerRepository;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.model.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.util.DateInterval;
import ru.bolgov.soulbeer.model.util.DateTemplate;
import ru.bolgov.soulbeer.model.util.ShowDate;
import ru.bolgov.soulbeer.service.ShiftService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private DateTemplate dateTemplate;

    private Date from = Date.valueOf("2021-01-01");
    private Date to = Date.valueOf("2021-01-02");


    @GetMapping("/all")
    public String allShifts(Model model){
        List<ShiftTemplate> shifts = shiftService.findAll();
        model.addAttribute("shifts", shifts);
        return "shifts/all";
    }

    @GetMapping("/filter")
    public String filterShifts(Model model){
//        this.from = dateInterval.getFrom().createDate();
//        this.to = dateInterval.getTo().createDate();
        List<ShiftTemplate> shifts = shiftService.findByInterval(from, to);
        model.addAttribute("shifts", shifts);
        return "shifts/all";
    }


    @GetMapping("/new")
    public String newShift(Model model){
        model.addAttribute("shiftTemplate", new ShiftTemplate());

        List<Seller> sellers = new ArrayList<>();
        try {
            sellers = sellerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sellers", sellers);
        model.addAttribute("dateTemplate", dateTemplate);

        return "shifts/new";
    }

    @PostMapping("/")
    public String createShift(@ModelAttribute("shiftTemplate") ShiftTemplate shiftTemplate){
        Shift shift = shiftTemplate.createShift();
        shiftService.save(shift);
        return "redirect:/shifts/all";
    }

    @GetMapping("/edit/{id}")
    public String editShift(@PathVariable("id") Long id, Model model){
        model.addAttribute("shiftTemplate", new ShiftTemplate(shiftService.findById(id)));
        List<Seller> sellers = new ArrayList<>();
        try {
            sellers = sellerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
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
        model.addAttribute("shift", new ShiftTemplate(shiftService.findById(id)));
        return "shifts/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long id){
        shiftService.delete(id);
        return "redirect:/shifts/all";
    }
}
