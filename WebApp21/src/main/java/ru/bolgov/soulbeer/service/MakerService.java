package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.maker.MakerDto;
import ru.bolgov.soulbeer.model.entity.MakerDebt;
import ru.bolgov.soulbeer.model.entity.ProductMaker;
import ru.bolgov.soulbeer.repository.MakerDebtRepository;
import ru.bolgov.soulbeer.repository.MakerRepository;
import ru.bolgov.soulbeer.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakerService {

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MakerDebtRepository makerDebtRepository;

    public List<MakerDto> findAll(){
        return makerRepository.findAll().stream()
                .map(x -> {
                    MakerDto makerDto = new MakerDto();
                    makerDto.setMaker(x);
                    makerDto.setProducts(productRepository.findByMakerId(x.getMakerId()));
                    makerDto.setCountProduct(productRepository.countByMakerId(x.getMakerId()));
                    makerDto.setMakerDebt(makerDebtRepository.findByMakerId(x.getMakerId()));
                    return makerDto;
                }).collect(Collectors.toList());
    }

    public MakerDto findById(Long makerId){
        ProductMaker productMaker = makerRepository.findById(makerId).get();
        MakerDto makerDto = new MakerDto();
        makerDto.setMaker(productMaker);
        makerDto.setProducts(productRepository.findByMakerId(makerId));
        makerDto.setCountProduct(productRepository.countByMakerId(makerId));
        makerDto.setMakerDebt(makerDebtRepository.findByMakerId(makerId));
        return makerDto;
    }

    public void saveMaker(ProductMaker maker){
        makerRepository.save(maker);
        Long makerId = makerRepository.findByName(maker.getMakerName()).get(0).getMakerId();
        MakerDebt makerDebt = new MakerDebt();
        makerDebt.setMakerId(makerId);
        makerDebt.setBalance(new BigDecimal(0));
        makerDebt.setTotalSumComing(new BigDecimal(0));
        makerDebt.setTotalSumPay(new BigDecimal(0));
        makerDebtRepository.save(makerDebt);
    }

    public void deleteById(Long makerId){
        makerRepository.deleteById(makerId);
    }

    public void editMaker(ProductMaker maker, Long id){
        ProductMaker tmp = makerRepository.findById(id).get();
        tmp.setMakerName(maker.getMakerName());
        tmp.setMakerContacts(maker.getMakerContacts());
        tmp.setMakerDescription(maker.getMakerDescription());
        makerRepository.save(tmp);
    }
}
