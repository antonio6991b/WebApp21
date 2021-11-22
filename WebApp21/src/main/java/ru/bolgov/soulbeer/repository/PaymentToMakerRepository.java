package ru.bolgov.soulbeer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.PaymentToMaker;

@Repository
public interface PaymentToMakerRepository extends CrudRepository<PaymentToMaker, Long> {
}
