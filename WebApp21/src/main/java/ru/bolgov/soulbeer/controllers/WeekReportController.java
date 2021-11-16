package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.report.ProductReportDto;
import ru.bolgov.soulbeer.model.dto.report.WeekReport;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.service.ProductReportService;
import ru.bolgov.soulbeer.service.ShiftService;

import java.util.List;


@Controller
@EnableWebMvc
@RequestMapping("/reports")
public class WeekReportController {

    @Autowired
    private ProductReportService productReportService;

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/{shiftId}")
    public String showReport(@PathVariable("shiftId") Long shiftId, Model model){

        WeekReport report = productReportService.getWeekReport(shiftId);

        model.addAttribute("weekReport", report);

        return "reports/show";
    }

    @GetMapping("/add-product/{shiftId}")
    public String addProduct(@PathVariable("shiftId") Long shiftId, Model model){
        ProductReport productReport = new ProductReport();
        productReport.setShiftId(shiftId);
        model.addAttribute("productReport", productReport);

        model.addAttribute("products", productReportService.getOtherProducts(shiftId));
        return "reports/product/new";
    }

    @PostMapping("/add-product/{shiftId}")
    public String saveProduct(@PathVariable("shiftId") Long shiftId,
            @ModelAttribute("productReport") ProductReport productReport){


        productReportService.save(productReport);
        return "redirect:/reports/"+productReport.getShiftId();
    }

    @GetMapping("/edit-product/{productId}")
    public String editProduct(@PathVariable("productId") Long productId,
                              Model model){
        ProductReport productReport = productReportService.getProductReport(productId);
        model.addAttribute("productName", productReportService.getProductName(productId));
        model.addAttribute("productReport", productReport);
        return "reports/product/edit";
    }

    @PostMapping("edit-product/{productId}")
    public String saveProduct(@ModelAttribute("productReport") ProductReport productReport,
                              @PathVariable("productId") Long productId){

        Long shiftId = productReportService.getShiftId(productId);

        productReport.setShiftId(shiftId);
        productReportService.edit(productReport, productId);
        return "redirect:/reports/" + shiftId;
    }

    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId,
                                Model model){
        ProductReport productReport = productReportService.getProductReport(productId);
        model.addAttribute("productReport", productReport);
        model.addAttribute("productName", productReportService.getProductName(productId));
        return "reports/product/delete";
    }

    @PostMapping("/delete-product/{productId}")
    public String confirmDelete(@PathVariable("productId") Long productId){
        Long shiftId = productReportService.getShiftId(productId);
        productReportService.deleteById(productId);
        return "redirect:/reports/" + shiftId;
    }
}
