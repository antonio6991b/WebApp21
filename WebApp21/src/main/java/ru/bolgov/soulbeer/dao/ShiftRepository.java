package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.shift.Shift;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    List<Shift> findAll();

    default void edit(Shift shift, Long id){
        Shift tmp = this.findById(id).orElse(null);
        if(Objects.nonNull(tmp)){
            tmp.setShiftSeller(shift.getShiftSeller());
            tmp.setShiftBegin(shift.getShiftBegin());
            tmp.setShiftEnds(shift.getShiftEnds());
            tmp.setCashBegin(shift.getCashBegin());
            tmp.setCashEnd(shift.getCashEnd());
            this.save(tmp);
        }
    }

    @Query("from Shift s where s.shiftBegin in(?1, ?2)")
    List<Shift> findAllByInterval(Date from, Date to);
}
