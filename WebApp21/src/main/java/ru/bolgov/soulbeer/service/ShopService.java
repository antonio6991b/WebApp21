package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.shop.StorageDto;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.repository.*;
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

    @Autowired
    private ShopSellersLinkRepository shopSellersLinkRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MakerRepository makerRepository;

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
                    shopDto.setCountSeller((long) shopSellersLinkRepository.findByShopId(shopDto.getShopId()).size());
                    return getShopDto(shopDto);
                })
                .collect(Collectors.toList());
        return shops;
    }

    private ShopDto getShopDto(ShopDto shopDto) {
        shopDto.setStorageList(storageRepository.findByShopId(shopDto.getShopId()).stream()
                .map(x -> {
                    StorageDto storageDto = new StorageDto();
                    storageDto.setStorage(x);
                    Product product = productRepository.findById(x.getProductId()).get();
                    storageDto.setProductName(product.getProductName());
                    storageDto.setMakerName(makerRepository.findById(product.getMakerId()).get().getMakerName());
                    return storageDto;
                })
                .collect(Collectors.toList()));
        return shopDto;
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
        return getShopDto(shopDto);
    }

    public void deleteById(Long id){
        if(shopSellersLinkRepository.findByShopId(id).size()==0) {
            shopRepository.deleteById(id);
        }
    }

    public Shop findByName(String name){
        return shopRepository.findByName(name);
    }


}
