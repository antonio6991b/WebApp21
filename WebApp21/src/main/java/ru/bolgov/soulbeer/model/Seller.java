package ru.bolgov.soulbeer.model;

import ru.bolgov.soulbeer.model.shift.Shift;

import javax.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sellerId")
    private Long sellerId;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @Column(name = "sellerName")
    private String sellerName;

    @Column(name = "sellerPhone")
    private Long sellerPhone;



    public Seller(){}

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", shop=" + shop +
                ", sellerName='" + sellerName + '\'' +
                ", sellerPhone=" + sellerPhone +
                '}';
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
}
