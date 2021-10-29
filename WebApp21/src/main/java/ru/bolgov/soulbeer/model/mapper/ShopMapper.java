package ru.bolgov.soulbeer.model.mapper;

import ru.bolgov.soulbeer.model.dto.shop.ShopDto;
import ru.bolgov.soulbeer.model.entity.Shop;

public class ShopMapper {



    public ShopDto entityToDto(Shop shop){
        ShopDto shopDto = new ShopDto();
        shopDto.setShopId(shop.getShopId());
        shopDto.setShopName(shop.getShopName().trim());
        shopDto.setShopPhone(shop.getShopPhone());
        shopDto.setShopAddress(shop.getShopAddress());
        shopDto.setIsCity(shop.getIsCity());
        return shopDto;
    }

    public Shop dtoToEntity(ShopDto shopDto){
        Shop shop = new Shop();
        shop.setShopId(shopDto.getShopId());
        shop.setShopName(shopDto.getShopName());
        shop.setShopPhone(shopDto.getShopPhone());
        shop.setShopAddress(shopDto.getShopAddress());
        shop.setIsCity(shopDto.getIsCity());
        return shop;
    }
}
