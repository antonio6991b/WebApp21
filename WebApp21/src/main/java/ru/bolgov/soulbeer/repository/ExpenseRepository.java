package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Expense;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    @Query("from Expense e where e.shiftId = ?1 and e.expenseType = ?2")
    List<Expense> findByType(Long shiftId, String expenseType);
}
