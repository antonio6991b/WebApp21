package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.report.ProductReport;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProductReportRepository extends CrudRepository<ProductReport, Long> {

    @Query("from ProductReport p where p.shift.shiftId = ?1")
    List<ProductReport> findAllByShift(Long shiftId);


    default void edit(ProductReport productReport, Long id){
        ProductReport tmp = this.findById(id).orElse(null);
        if(Objects.nonNull(tmp)){
            tmp.setProduct(productReport.getProduct());
            tmp.setPriceBuy(productReport.getPriceBuy());
            tmp.setPriceSell(productReport.getPriceSell());
            tmp.setRemainsLast(productReport.getRemainsLast());
            tmp.setComing(productReport.getComing());
            tmp.setRemainsCurrent(productReport.getRemainsCurrent());
            tmp.setSumCurrent(productReport.getSumCurrent());
            tmp.setGrossProfit(productReport.getGrossProfit());
            tmp.setNotebookValue(productReport.getNotebookValue());
            tmp.setBalance(productReport.getBalance());
            this.save(tmp);
        }
    }

}
