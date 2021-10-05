package ru.bolgov.soulbeer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="shops")
public class Shop {

    @Id
    @Column(name = "shopId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shopId;

    @Column(name="shopName")
    private String shopName;

    @Column(name="shopAddress")
    private String shopAddress;

    @Column(name="shopPhone")
    private Long shopPhone;

    @Column(name="isCity")
    private int isCity;

    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private List<Seller> sellers;

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }


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
