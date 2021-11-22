package ru.bolgov.soulbeer.model.dto.shop;


import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.entity.Storage;

import java.util.List;

public class ShopDto {

    private Long shopId;

    private String shopName;

    private String shopAddress;

    private Long shopPhone;

    private Long countSeller;

    private int isCity;

    private List<StorageDto> storageList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Long getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(Long shopPhone) {
        this.shopPhone = shopPhone;
    }

    public int getIsCity() {
        return isCity;
    }

    public void setIsCity(int isCity) {
        this.isCity = isCity;
    }

    public Long getCountSeller() {
        return countSeller;
    }

    public void setCountSeller(Long countSeller) {
        this.countSeller = countSeller;
    }

    public List<StorageDto> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<StorageDto> storageList) {
        this.storageList = storageList;
    }
}
