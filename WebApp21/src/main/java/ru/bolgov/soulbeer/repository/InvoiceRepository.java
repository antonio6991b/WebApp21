package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Invoice;

import java.sql.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    @Query("from Invoice invoice where invoice.shopId = ?1 and invoice.date = ?2")
    List<Invoice> findByShopIdDate(Long shopId, Date date);

    @Query("from Invoice invoice, Shift shift where invoice.date in (shift.shiftBegin, shift.shiftEnds) and shift.shiftId = ?1 order by invoice.date")
    List<Invoice> findByShiftId(Long shiftId);

    List<Invoice> findAll();
}
