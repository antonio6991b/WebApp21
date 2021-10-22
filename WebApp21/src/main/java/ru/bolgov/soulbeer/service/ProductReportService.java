package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.repository.ProductReportRepository;

import java.util.Objects;

@Service
public class ProductReportService {

    @Autowired
    private ProductReportRepository productReportRepository;


    public void edit(ProductReport productReport, Long id) {
        ProductReport tmp = productReportRepository.findById(id).orElse(null);
        if (Objects.nonNull(tmp)) {
            tmp.setProductId(productReport.getProductId());
            tmp.setPriceBuy(productReport.getPriceBuy());
            tmp.setPriceSell(productReport.getPriceSell());
            tmp.setRemainsLast(productReport.getRemainsLast());
            tmp.setComing(productReport.getComing());
            tmp.setRemainsCurrent(productReport.getRemainsCurrent());
            tmp.setSumCurrent(productReport.getSumCurrent());
            tmp.setGrossProfit(productReport.getGrossProfit());
            tmp.setNotebookValue(productReport.getNotebookValue());
            tmp.setBalance(productReport.getBalance());
            productReportRepository.save(tmp);
        }
    }

}
