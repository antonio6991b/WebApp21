package ru.bolgov.soulbeer.model.dto.invoice;

import ru.bolgov.soulbeer.model.dto.util.DateTemplate;
import ru.bolgov.soulbeer.model.dto.util.ShowDate;
import ru.bolgov.soulbeer.model.entity.Invoice;
import ru.bolgov.soulbeer.model.entity.InvoiceProduct;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {
    private Invoice invoice;
    private ShowDate date;
    private String makerName;
    private String shopName;
    private List<InvoiceProductDto> products;

    public InvoiceDto(){
        LocalDate today = LocalDate.now();
        this.date = new ShowDate(today.getDayOfMonth(), today.getMonth().getValue(), today.getYear());
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ShowDate getDate() {
        return date;
    }

    public void setDate(ShowDate date) {
        this.date = date;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<InvoiceProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<InvoiceProductDto> products) {
        this.products = products;
    }
}
