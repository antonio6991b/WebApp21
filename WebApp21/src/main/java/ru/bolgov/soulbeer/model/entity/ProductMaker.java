package ru.bolgov.soulbeer.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "maker")
public class ProductMaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maker_id")
    private Long makerId;

    @Column(name = "maker_name")
    private String makerName;

    @Column(name = "maker_contacts")
    private String makerContacts;

    @Column(name = "maker_description")
    private String makerDescription;

    public Long getMakerId() {
        return makerId;
    }

    public void setMakerId(Long makerId) {
        this.makerId = makerId;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getMakerContacts() {
        return makerContacts;
    }

    public void setMakerContacts(String makerContacts) {
        this.makerContacts = makerContacts;
    }

    public String getMakerDescription() {
        return makerDescription;
    }

    public void setMakerDescription(String makerDescription) {
        this.makerDescription = makerDescription;
    }
}
