package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Shift;

import java.sql.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    List<Shift> findAll();

    @Query("from Shift s where s.shiftBegin in(?1, ?2)")
    List<Shift> findAllByInterval(Date from, Date to);
}
