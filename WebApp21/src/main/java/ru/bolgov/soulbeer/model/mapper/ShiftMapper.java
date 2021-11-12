package ru.bolgov.soulbeer.model.mapper;

import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.entity.Shift;

public class ShiftMapper {

    public Shift dtoToEntity(ShiftTemplate shiftTemplate) {
        Shift shift = shiftTemplate.getShift();
        shift.setShopId(shiftTemplate.getShopId());
        return shift;
    }
}
