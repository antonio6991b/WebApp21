package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_product")
public class InvoiceProduct {

    @Id
    @Column(name = "invoice_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceProductId;

    private Long invoiceId;

    private Long productId;

    private BigDecimal priceBuy;

    private BigDecimal count;

    public Long getInvoiceProductId() {
        return invoiceProductId;
    }

    public void setInvoiceProductId(Long invoiceProductId) {
        this.invoiceProductId = invoiceProductId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(BigDecimal priceBuy) {
        this.priceBuy = priceBuy;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
