package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.dao.ShiftRepository;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.model.shift.ShiftTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<ShiftTemplate> findAll(){
        List<Shift> shifts = new ArrayList<>();
        try {
            shifts = shiftRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<ShiftTemplate> templates = new ArrayList<>();
        for(int i = 0; i < shifts.size(); i++){
            templates.add(new ShiftTemplate(shifts.get(i)));
        }
        return templates;
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
