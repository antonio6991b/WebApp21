package ru.bolgov.soulbeer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.MakerDebt;

@Repository
public interface MakerDebtRepository extends CrudRepository<MakerDebt, Long> {
}
