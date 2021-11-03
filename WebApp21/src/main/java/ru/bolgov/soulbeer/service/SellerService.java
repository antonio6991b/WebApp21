package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.repository.SellerRepository;
import ru.bolgov.soulbeer.repository.ShopRepository;
import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.entity.Seller;
import ru.bolgov.soulbeer.model.entity.Shop;
import ru.bolgov.soulbeer.model.mapper.SellerMapper;
import ru.bolgov.soulbeer.model.mapper.ShopMapper;

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

    private SellerMapper sellerMapper = new SellerMapper();
    private ShopMapper shopMapper = new ShopMapper();

    public void edit(SellerDto seller, Long id){
        Seller tmpSeller = sellerRepository.findById(id).orElse(null);
        if(Objects.nonNull(tmpSeller)){
            tmpSeller.setSellerName(seller.getSellerName());
            tmpSeller.setShopId(seller.getShopId());
            tmpSeller.setSellerPhone(seller.getSellerPhone());
            sellerRepository.save(tmpSeller);
        }
    }

    public List<SellerDto> findByShopId(Long shopId){
        return sellerRepository.findByShopId(shopId)
                .stream()
                .map(x -> {
                    SellerDto sellerDto = sellerMapper.sellerToDto(x);
                    sellerDto.setShopName(shopRepository.findById(sellerDto.getShopId()).orElse(null).getShopName());
                    return sellerDto;
                })
                .collect(Collectors.toList());
    }

    public List<SellerDto> findAll(){

        return sellerRepository.findAll()
                .stream()
                .map(x -> {
                    SellerDto sellerDto = sellerMapper.sellerToDto(x);
                    sellerDto.setShopName(shopRepository.findById(sellerDto.getShopId()).orElse(new Shop()).getShopName());
                    return sellerDto;
                })
                .collect(Collectors.toList());
    }

    public void save(SellerDto sellerDto){
        boolean isPresent = sellerRepository.findByShopId(sellerDto.getShopId()).stream()
                .map(x -> x.getSellerName().trim().toLowerCase(Locale.ROOT))
                .anyMatch(x -> (x.equalsIgnoreCase(sellerDto.getSellerName().trim().toLowerCase(Locale.ROOT))));
        if (!isPresent){
            sellerRepository.save(sellerMapper.dtoToSeller(sellerDto));
        }
    }

    public SellerDto findById(Long id){
        SellerDto sellerDto = sellerMapper.sellerToDto(sellerRepository.findById(id).orElse(new Seller()));
        sellerDto.setShopName(shopRepository.findById(sellerDto.getShopId()).orElse(new Shop()).getShopName());
        return sellerDto;
    }

    public void deleteById(Long id){
        sellerRepository.deleteById(id);
    }
}
