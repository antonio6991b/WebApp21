package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.report.WeekReport;
import ru.bolgov.soulbeer.model.entity.Expense;
import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.service.ProductReportService;
import ru.bolgov.soulbeer.service.ShiftService;


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

    @GetMapping("/add-write-off/{shiftId}")
    public String addWriteOff(@PathVariable("shiftId") Long shiftId, Model model){
        Expense expense = new Expense();
        expense.setShiftId(shiftId);
        expense.setExpenseType("writeoff");
        model.addAttribute("expense", expense);

        return "reports/expenses/new";
    }

    @GetMapping("/add-cash-desk/{shiftId}")
    public String addCashDesk(@PathVariable("shiftId") Long shiftId, Model model){
        Expense expense = new Expense();
        expense.setShiftId(shiftId);
        expense.setExpenseType("cashdesk");
        model.addAttribute("expense", expense);

        return "reports/expenses/new";
    }

    @PostMapping("/add-expense/{shiftId}")
    public String saveWriteOff(@PathVariable("shiftId") Long shiftId,
                              @ModelAttribute("expense") Expense expense){

        productReportService.saveExpense(expense, expense.getExpenseType());
        return "redirect:/reports/" + shiftId;
    }

    @GetMapping("/edit-expense/{expenseId}")
    public String editExpense(@PathVariable("expenseId") Long expenseId, Model model){
        Expense expense = productReportService.findExpense(expenseId);
        model.addAttribute("expense", expense);

        return "reports/expenses/edit";
    }

    @PostMapping("/edit-expense/{expenseId}")
    public String editExpense(@PathVariable("expenseId") Long expenseId,
                               @ModelAttribute("expense") Expense expense){

        productReportService.editExpense(expense, expenseId);
        return "redirect:/reports/" + expense.getShiftId();
    }

    @GetMapping("/delete-expense/{expenseId}")
    public String deleteExpense(@PathVariable("expenseId") Long expenseId){
        Long shiftId = productReportService.findExpense(expenseId).getShiftId();
        productReportService.deleteExpense(expenseId);
        return "redirect:/reports/" + shiftId;
    }

}
