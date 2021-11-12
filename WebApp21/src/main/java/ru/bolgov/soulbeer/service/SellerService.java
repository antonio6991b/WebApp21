package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.entity.ShopSellerLink;
import ru.bolgov.soulbeer.repository.SellerRepository;
import ru.bolgov.soulbeer.repository.ShopRepository;
import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.entity.Seller;
import ru.bolgov.soulbeer.model.entity.Shop;
import ru.bolgov.soulbeer.model.mapper.SellerMapper;
import ru.bolgov.soulbeer.model.mapper.ShopMapper;
import ru.bolgov.soulbeer.repository.ShopSellersLinkRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopSellersLinkRepository shopSellersLinkRepository;

    private SellerMapper sellerMapper = new SellerMapper();
    private ShopMapper shopMapper = new ShopMapper();

    public void edit(SellerDto seller, Long id){
        Seller tmpSeller = sellerRepository.findById(id).orElse(null);
        if(Objects.nonNull(tmpSeller)){
            tmpSeller.setSellerName(seller.getSellerName());
            tmpSeller.setSellerPhone(seller.getSellerPhone());
            sellerRepository.save(tmpSeller);
        }
    }

    public List<SellerDto> findByShopId(Long shopId){
        return findSellersByShopId(shopId)
                .stream()
                .map(x -> {
                    SellerDto sellerDto = sellerMapper.sellerToDto(x);
                    sellerDto.setShopIds(findShopIds(sellerDto.getSellerId()));
                    sellerDto.setShopNames(findShopNames(sellerDto.getSellerId()));
                    return sellerDto;
                })
                .collect(Collectors.toList());
    }

    public List<SellerDto> findAll(){

        return sellerRepository.findAll()
                .stream()
                .map(x -> {
                    SellerDto sellerDto = sellerMapper.sellerToDto(x);
                    sellerDto.setShopIds(findShopIds(sellerDto.getSellerId()));
                    sellerDto.setShopNames(findShopNames(sellerDto.getSellerId()));
                    return sellerDto;
                })
                .collect(Collectors.toList());
    }

    public void save(SellerDto sellerDto){
        Seller seller = sellerMapper.dtoToSeller(sellerDto);
        sellerRepository.save(seller);
    }

    public SellerDto findById(Long id){
        SellerDto sellerDto = sellerMapper.sellerToDto(sellerRepository.findById(id).orElse(new Seller()));
        sellerDto.setShopIds(findShopIds(sellerDto.getSellerId()));
        sellerDto.setShopNames(findShopNames(sellerDto.getSellerId()));
        return sellerDto;
    }

    public void deleteById(Long id){
        if(shopSellersLinkRepository.findBySellerId(id).size()==0) {
            sellerRepository.deleteById(id);
        }
    }

    public void addSellerToShop(Long sellerId, Long shopId){
        if(shopSellersLinkRepository.countLink(sellerId, shopId).size()==0
                && Objects.nonNull(sellerId)
                && Objects.nonNull(shopId)){
            ShopSellerLink shopSellerLink = new ShopSellerLink();
            shopSellerLink.setSellerId(sellerId);
            shopSellerLink.setShopId(shopId);
            shopSellersLinkRepository.save(shopSellerLink);
        }
    }

    public void removeSellerFromShop(Long sellerId, Long shopId){

        if(shopSellersLinkRepository.countLink(sellerId, shopId).size()>0){
            ShopSellerLink shopSellerLinks = shopSellersLinkRepository.countLink(sellerId, shopId).get(0).orElse(null);
            shopSellersLinkRepository.deleteById(shopSellerLinks.getLinkId());
        }
    }

    private List<Seller> findSellersByShopId(Long shopId){
        return shopSellersLinkRepository.findByShopId(shopId).stream()
                .map(x -> sellerRepository.findById(x.getSellerId()).orElse(null))
                .collect(Collectors.toList());
    }

    private List<Long> findShopIds(Long sellerId){
        return shopSellersLinkRepository.findBySellerId(sellerId).stream()
                .map(x -> x.getShopId())
                .collect(Collectors.toList());
    }

    private List<String> findShopNames(Long sellerId){
        return this.findShopIds(sellerId).stream()
                .map(x -> shopRepository.findById(x).orElse(null).getShopName())
                .collect(Collectors.toList());
    }

    public List<Shop> findOtherShops(Long sellerId){
        List<Long> myShops = findShopIds(sellerId);
        return shopRepository.findAll().stream()
                .filter(x->!myShops.contains(x.getShopId()))
                .collect(Collectors.toList());
    }

    public List<Shop> findMyShops(Long sellerId){
        List<Long> myShops = findShopIds(sellerId);
        return shopRepository.findAll().stream()
                .filter(x->myShops.contains(x.getShopId()))
                .collect(Collectors.toList());
    }
}
