package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.model.mapper.ShiftMapper;
import ru.bolgov.soulbeer.repository.SellerRepository;
import ru.bolgov.soulbeer.repository.ShiftRepository;
import ru.bolgov.soulbeer.repository.ShopRepository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ShiftService {


    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SellerRepository sellerRepository;

    private ShiftMapper shiftMapper = new ShiftMapper();

    public List<ShiftTemplate> findAll(){
        return shiftRepository.findAll().stream()
                .map(x -> {
                    ShiftTemplate shiftTemplate = new ShiftTemplate(x);
                    shiftTemplate.setShopName(shopRepository.findById(shiftTemplate.getShift().getShopId()).get().getShopName());
                    shiftTemplate.setShopPhone(shopRepository.findById(shiftTemplate.getShift().getShopId()).get().getShopPhone());
                    shiftTemplate.setSellerName(sellerRepository.findById(shiftTemplate.getShift().getSellerId()).get().getSellerName());
                    return shiftTemplate;
                })
                .collect(Collectors.toList());
    }

    public Shift newShift(){
        return new Shift();
    }

    public void save(ShiftTemplate shiftTemplate){
        Shift shift = shiftTemplate.createShift();
        shift.setShopId(sellerRepository.findById(shift.getSellerId()).get().getShopId());
        shiftRepository.save(shift);
    }

    public ShiftTemplate findById(Long id){
        Shift shift =  shiftRepository.findById(id).orElse(newShift());
        ShiftTemplate shiftTemplate = new ShiftTemplate(shift);
        shiftTemplate.setSellerName(sellerRepository.findById(shift.getSellerId()).get().getSellerName());
        shiftTemplate.setShopName(shopRepository.findById(sellerRepository.findById(shift.getSellerId()).get().getShopId()).get().getShopName());
        return shiftTemplate;
    }

    public ShiftService(){}

    public void delete(Long id){
        shiftRepository.deleteById(id);
    }

    public List<ShiftTemplate> findByInterval(Date from, Date to){
        return shiftRepository.findAllByInterval(from, to).stream()
                .map(x -> {
                    ShiftTemplate shiftTemplate = new ShiftTemplate(x);
                    shiftTemplate.setShopName(shopRepository.findById(shiftTemplate.getShift().getShopId()).get().getShopName());
                    shiftTemplate.setShopPhone(shopRepository.findById(shiftTemplate.getShift().getShopId()).get().getShopPhone());
                    shiftTemplate.setSellerName(sellerRepository.findById(shiftTemplate.getShift().getSellerId()).get().getSellerName());
                    return shiftTemplate;
                })
                .collect(Collectors.toList());
    }

    public void edit(Shift shift, Long id){
        Shift tmp = shiftRepository.findById(id).orElse(null);
        if(Objects.nonNull(tmp)){
            tmp.setSellerId(shift.getSellerId());
            tmp.setShiftBegin(shift.getShiftBegin());
            tmp.setShiftEnds(shift.getShiftEnds());
            tmp.setCashBegin(shift.getCashBegin());
            tmp.setCashEnd(shift.getCashEnd());
            shiftRepository.save(tmp);
        }
    }
}
