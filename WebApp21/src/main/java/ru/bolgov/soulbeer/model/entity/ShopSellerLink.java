package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "shop_seller_link")
public class ShopSellerLink {
    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "seller_id")
    private Long sellerId;

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
