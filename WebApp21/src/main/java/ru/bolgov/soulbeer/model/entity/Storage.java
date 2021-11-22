package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @Column(name = "storage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storageId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price_buy")
    private BigDecimal priceBuy;

    @Column(name = "count")
    private BigDecimal count;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
