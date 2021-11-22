package ru.bolgov.soulbeer.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bolgov.soulbeer.model.dto.invoice.InvoiceDto;
import ru.bolgov.soulbeer.model.dto.maker.MakerDto;
import ru.bolgov.soulbeer.model.dto.util.DateTemplate;
import ru.bolgov.soulbeer.model.entity.Invoice;
import ru.bolgov.soulbeer.model.entity.InvoiceProduct;
import ru.bolgov.soulbeer.service.InvoiceService;
import ru.bolgov.soulbeer.service.MakerService;
import ru.bolgov.soulbeer.service.ProductService;
import ru.bolgov.soulbeer.service.ShopService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private MakerService makerService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;


    @GetMapping("new")
    private String newInvoice(@RequestParam Map<String, String> headers, Model model){
        InvoiceDto invoiceDto = new InvoiceDto();
        Invoice invoice = new Invoice();
        Long shopId = shopService.findAll().get(0).getShopId();
        Long makerId = makerService.findAll().get(0).getMaker().getMakerId();
        try {
            shopId = Long.valueOf(headers.get("shopId"));
        }catch (Exception e){
            //ignore
        }
        try {
            makerId = Long.valueOf(headers.get("makerId"));
        }catch (Exception e){
            //ignore
        }
        invoice.setShopId(shopId);
        invoice.setMakerId(makerId);
        invoiceDto.setInvoice(invoice);
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("dateTemplate", new DateTemplate());
        model.addAttribute("shops", shopService.findAll());
        model.addAttribute("makers", makerService.findAll());

        return "invoices/new";
    }

    @PostMapping("/new")
    public String createInvoice(@ModelAttribute("invoice")InvoiceDto invoiceDto){
        invoiceService.save(invoiceDto);
        Long shopId = invoiceDto.getInvoice().getShopId();
        Date date = invoiceDto.getInvoice().getDate();
        Long invoiceId = invoiceService.findByShopIdDate(shopId, date).getInvoice().getInvoiceId();
        return "redirect:/invoice/show/"+invoiceId;
    }

    @GetMapping("show/{invoiceId}")
    public String showInvoice(Model model, @PathVariable("invoiceId") Long invoiceId){
        model.addAttribute("invoice", invoiceService.findById(invoiceId));
        return "invoices/show";
    }

    @GetMapping("/all")
    public String allInvoices(Model model){

        model.addAttribute("invoices", invoiceService.findAll());
        return "invoices/all";
    }

    @GetMapping("/add-product/{invoiceId}")
    public String addProduct(Model model, @PathVariable("invoiceId") Long invoiceId){

        InvoiceProduct invoiceProduct = new InvoiceProduct();
        Invoice invoice = invoiceService.findById(invoiceId).getInvoice();
        invoiceProduct.setInvoiceId(invoiceId);
        model.addAttribute("invoiceProduct", invoiceProduct);
        model.addAttribute("products", productService.findByMakerId(invoice.getMakerId()));

        return "invoices/product/add";
    }

    @PostMapping("/add-product")
    public String saveProduct(@ModelAttribute("invoiceProduct")InvoiceProduct invoiceProduct){
        Long invoiceId = invoiceProduct.getInvoiceId();
        invoiceService.save(invoiceProduct);
        return "redirect:/invoice/show/" + invoiceId;
    }

}
