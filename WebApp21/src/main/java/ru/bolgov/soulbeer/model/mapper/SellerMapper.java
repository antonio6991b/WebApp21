package ru.bolgov.soulbeer.model.mapper;

import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.entity.Seller;

public class SellerMapper {



    private ShopMapper shopMapper = new ShopMapper();

    public SellerDto sellerToDto(Seller seller){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setSellerId(seller.getSellerId());
        sellerDto.setSellerName(seller.getSellerName().trim());
        sellerDto.setSellerPhone(seller.getSellerPhone());
        return sellerDto;
    }

    public Seller dtoToSeller(SellerDto sellerDto){
        Seller seller = new Seller();
        seller.setSellerName(sellerDto.getSellerName().trim());
        seller.setSellerPhone(sellerDto.getSellerPhone());
        return seller;
    }
}
