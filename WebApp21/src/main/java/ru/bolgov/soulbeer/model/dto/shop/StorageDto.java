package ru.bolgov.soulbeer.model.dto.shop;

import ru.bolgov.soulbeer.model.entity.Storage;

public class StorageDto {
    private Storage storage;
    private String productName;
    private String makerName;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }
}
