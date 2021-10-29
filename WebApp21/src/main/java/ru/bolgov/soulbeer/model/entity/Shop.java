package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="shop")
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Column(name="shop_name")
    private String shopName;

    @Column(name="shop_address")
    private String shopAddress;

    @Column(name="shop_phone")
    private Long shopPhone;

    @Column(name="is_city")
    private int isCity;

    public Shop(){}

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName(){ return shopName; }

    public void setShopName(String shopName){ this.shopName = shopName; }

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

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName=" +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopPhone=" + shopPhone +
                ", isCity=" + isCity +
                '}';
    }
}
