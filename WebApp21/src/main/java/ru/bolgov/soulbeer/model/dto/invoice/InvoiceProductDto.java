package ru.bolgov.soulbeer.model.dto.invoice;

import ru.bolgov.soulbeer.model.entity.InvoiceProduct;

public class InvoiceProductDto {
    private InvoiceProduct invoiceProduct;
    private String productName;

    public InvoiceProduct getInvoiceProduct() {
        return invoiceProduct;
    }

    public void setInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProduct = invoiceProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
