package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.dao.ShiftRepository;
import ru.bolgov.soulbeer.model.shift.Shift;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> findAll(){
        List<Shift> shifts = new ArrayList<>();
        try {
            shifts = shiftRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return shifts;
    }

    public Shift newShift(){
        return new Shift();
    }

    public void save(Shift shift){
        shiftRepository.save(shift);
    }

    public Shift findById(Long id){
        return shiftRepository.findById(id).orElse(newShift());
    }

    public void edit(Shift shift, Long id){
        shiftRepository.edit(shift, id);
    }

    public void delete(Long id){
        shiftRepository.deleteById(id);
    }
}
