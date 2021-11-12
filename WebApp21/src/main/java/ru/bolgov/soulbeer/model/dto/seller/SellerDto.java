package ru.bolgov.soulbeer.model.dto.seller;

import ru.bolgov.soulbeer.model.dto.shop.ShopDto;

import java.util.List;

public class SellerDto {

    private Long sellerId;

    private List<String> shopNames;

    private List<Long> shopIds;

    private String sellerName;

    private Long sellerPhone;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(Long sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public List<String> getShopNames() {
        return shopNames;
    }

    public void setShopNames(List<String> shopNames) {
        this.shopNames = shopNames;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }
}
