package ru.bolgov.soulbeer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.SellerRepository;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.service.ShiftService;

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

    @GetMapping("/all")
    public String allShifts(Model model){
        List<Shift> shifts = shiftService.findAll();
        model.addAttribute("shifts", shifts);
        return "shifts/all";
    }

    @GetMapping("/new")
    public String newShift(Model model){
        model.addAttribute("shift", shiftService.newShift());

        List<Seller> sellers = new ArrayList<>();
        try {
            sellers = sellerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sellers", sellers);

        return "shifts/new";
    }

    @PostMapping("/")
    public String createShift(@ModelAttribute("shift") Shift shift){
        shiftService.save(shift);
        return "redirect:/shifts/all";
    }

    @GetMapping("/edit/{id}")
    public String editShift(@PathVariable("id") Long id, Model model){
        model.addAttribute("shift", shiftService.findById(id));
        List<Seller> sellers = new ArrayList<>();
        try {
            sellers = sellerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("sellers", sellers);
        return "shifts/edit";
    }

    @PostMapping("/edit/{id}")
    public String confirmEdit(@PathVariable("id") Long id, @ModelAttribute Shift shift){
        shiftService.edit(shift, id);
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
