package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.InvoiceProduct;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends CrudRepository<InvoiceProduct, Long> {

    @Query("from InvoiceProduct i where i.invoiceId = ?1")
    List<InvoiceProduct> findByInvoiceId(Long invoiceId);

}
