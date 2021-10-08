package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.dao.ProductReportRepository;
import ru.bolgov.soulbeer.dao.ProductRepository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.report.ProductReport;
import ru.bolgov.soulbeer.model.report.WeekReport;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.model.shift.ShiftTemplate;
import ru.bolgov.soulbeer.service.ShiftService;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/reports")
public class WeekReportController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReportRepository productReportRepository;

    @GetMapping("/{shiftId}")
    public String showReport(@PathVariable("shiftId") Long shiftId, Model model){

        Shift shift = shiftService.findById(shiftId);
        ShiftTemplate template = new ShiftTemplate(shift);
        WeekReport report = new WeekReport();
        report.setShiftTemplate(template);
        List<ProductReport> products = productReportRepository.findAllByShift(template.getShift().getShiftId());
        report.setProducts(products);

        model.addAttribute("weekReport", report);

        return "reports/show";
    }

    @GetMapping("/add-product/{shiftId}")
    public String addProduct(@PathVariable("shiftId") Long shiftId, Model model){
        ProductReport productReport = new ProductReport();
        Shift shift = shiftService.findById(shiftId);
        productReport.setShift(shift);
        model.addAttribute("productReport", productReport);
        WeekReport report = new WeekReport();
        ShiftTemplate template = new ShiftTemplate(shift);
        List<ProductReport> productReports = productReportRepository.findAllByShift(shiftId);
        report.setProducts(productReports);
        List<Product> newProducts = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for(int i = 0; i < products.size(); i++){
            boolean flag = false;
            for( int j = 0; j < productReports.size(); j++){
                if(products.get(i).getProductId() == productReports.get(j).getProduct().getProductId()){
                    flag = true;
                }
            }
            if(!flag){
                newProducts.add(products.get(i));
            }
        }

        model.addAttribute("products", newProducts);
        return "reports/product/new";
    }

    @PostMapping("/add-product/{shiftId}")
    public String saveProduct(@PathVariable("shiftId") Long shiftId,
            @ModelAttribute("productReport") ProductReport productReport,
                              @ModelAttribute("product") Product product){
        productReport.setShift(shiftService.findById(shiftId));
        productReportRepository.save(productReport);
        return "redirect:/reports/"+shiftId;
    }

    @GetMapping("/edit-product/{productId}")
    public String editProduct(@PathVariable("productId") Long productId,
                              Model model){
        ProductReport productReport = productReportRepository.findById(productId).orElse(new ProductReport());
        Long shiftId = productReport.getShift().getShiftId();
        Shift shift = shiftService.findById(shiftId);
        productReport.setShift(shift);
        model.addAttribute("productReport", productReport);

        List<ProductReport> productReports = productReportRepository.findAllByShift(shiftId);

        List<Product> newProducts = new ArrayList<>();
        newProducts.add(productReport.getProduct());
        List<Product> products = productRepository.findAll();

        for(int i = 0; i < products.size(); i++){
            boolean flag = false;
            for( int j = 0; j < productReports.size(); j++){
                if(products.get(i).getProductId() == productReports.get(j).getProduct().getProductId()){
                    flag = true;
                }
            }
            if(!flag){
                newProducts.add(products.get(i));
            }
        }

        model.addAttribute("products", newProducts);
        return "reports/product/edit";
    }

    @PostMapping("edit-product/{productId}")
    public String saveProduct(@ModelAttribute("productReport") ProductReport productReport,
                              @PathVariable("productId") Long productId){

        Long shiftId = productReportRepository.findById(productId).orElse(new ProductReport()).getShift().getShiftId();

        productReportRepository.edit(productReport, productId);
        return "redirect:/reports/" + shiftId;
    }

    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId,
                                Model model){
        ProductReport productReport = productReportRepository.findById(productId).orElse(new ProductReport());
        model.addAttribute("productReport", productReport);
        return "reports/product/delete";
    }

    @PostMapping("/delete-product/{productId}")
    public String confirmDelete(@PathVariable("productId") Long productId){
        Long shiftId = productReportRepository.findById(productId).orElse(new ProductReport()).getShift().getShiftId();
        productReportRepository.deleteById(productId);
        return "redirect:/reports/" + shiftId;
    }
}
