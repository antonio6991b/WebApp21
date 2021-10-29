package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.ProductReport;

import java.util.List;

@Repository
public interface ProductReportRepository extends CrudRepository<ProductReport, Long> {

    @Query("from ProductReport p where p.shiftId = ?1")
    List<ProductReport> findAllByShift(Long shiftId);

    boolean existsByProductIdAndShiftId(Long productId, Long shiftId);

}
