package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.maker.MakerDto;
import ru.bolgov.soulbeer.model.entity.ProductMaker;
import ru.bolgov.soulbeer.service.MakerService;

@Controller
@EnableWebMvc
@RequestMapping("/makers")
public class MakerController {

    @Autowired
    private MakerService makerService;

    @GetMapping("all")
    public String allMakers(Model model){
        model.addAttribute("makers", makerService.findAll());
        return "makers/all";
    }

    @GetMapping("/new")
    public String newMaker(Model model){
        model.addAttribute("maker", new ProductMaker());
        return "makers/new";
    }

    @PostMapping("/")
    public String createMaker(@ModelAttribute("maker") ProductMaker maker){
        makerService.saveMaker(maker);
        return "redirect:/makers/all/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaker(@PathVariable("id") Long makerId, Model model){
        model.addAttribute("maker", makerService.findById(makerId).getMaker());
        return "makers/delete";
    }

    @PostMapping("/delete/{id}")
    public String confirmDeleteMaker(@PathVariable("id") Long makerId){
        makerService.deleteById(makerId);
        return "redirect:/makers/all";
    }

    @GetMapping("/edit/{id}")
    public String editMaker(@PathVariable("id") Long makerId, Model model){
        model.addAttribute("maker", makerService.findById(makerId).getMaker());
        return "makers/edit";
    }

    @PostMapping("/edit/{id}")
    public String confirmEditMaker(@PathVariable("id") Long makerId, @ModelAttribute("maker") ProductMaker maker){
        makerService.editMaker(maker, makerId);
        return "redirect:/makers/all";
    }




}























