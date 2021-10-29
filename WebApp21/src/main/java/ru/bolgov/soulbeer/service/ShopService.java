package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.repository.SellerRepository;
import ru.bolgov.soulbeer.repository.ShopRepository;
import ru.bolgov.soulbeer.model.dto.shop.ShopDto;
import ru.bolgov.soulbeer.model.entity.Shop;
import ru.bolgov.soulbeer.model.mapper.SellerMapper;
import ru.bolgov.soulbeer.model.mapper.ShopMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SellerRepository sellerRepository;

    private ShopMapper shopMapper = new ShopMapper();
    private SellerMapper sellerMapper = new SellerMapper();

    public ShopService(){

    }

    public void edit(ShopDto shop, Long id){
        Shop tmpShop = shopRepository.findById(id).orElse(null);
        if(Objects.nonNull(tmpShop)){
            tmpShop.setShopName(shop.getShopName().trim());
            tmpShop.setShopAddress(shop.getShopAddress());
            tmpShop.setShopPhone(shop.getShopPhone());
            tmpShop.setIsCity(shop.getIsCity());
            shopRepository.save(tmpShop);
        }
    }

    public List<ShopDto> findAll(){
        List<ShopDto> shops = new ArrayList<>();
        shops = shopRepository.findAll()
                .stream()
                .map(x -> {
                    ShopDto shopDto = shopMapper.entityToDto(x);
                    shopDto.setCountSeller(sellerRepository.countByShopId(x.getShopId()));
                    return shopDto;
                })
                .collect(Collectors.toList());
        return shops;
    }

    public void save(ShopDto shopDto){
        boolean isPresent = shopRepository.findAll().stream()
                .map(x -> x.getShopName().trim().toLowerCase(Locale.ROOT))
                .anyMatch(x -> (x.equalsIgnoreCase(shopDto.getShopName().trim().toLowerCase(Locale.ROOT))));

        if (!isPresent){
            shopRepository.save(shopMapper.dtoToEntity(shopDto));
        }
    }

    public ShopDto findById(Long id){
        Shop shop = shopRepository.findById(id).orElse(new Shop());
        ShopDto shopDto =  shopMapper.entityToDto(shop);
        return shopDto;
    }

    public void deleteById(Long id){
        shopRepository.deleteById(id);
    }
}
