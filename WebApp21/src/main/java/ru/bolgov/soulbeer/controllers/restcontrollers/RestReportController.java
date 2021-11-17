package ru.bolgov.soulbeer.controllers.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.soulbeer.model.dto.report.rest.ReportValue;
import ru.bolgov.soulbeer.model.entity.Shift;
import ru.bolgov.soulbeer.service.ProductReportService;
import ru.bolgov.soulbeer.service.ShiftService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@RestController
public class RestReportController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ProductReportService productReportService;

    @GetMapping("/report-values")
    public ReportValue getReportValues(@RequestParam Map<String, String> headers) throws Exception {
        ReportValue reportValue;
        Long productId = Long.valueOf(headers.get("productId"));
        Long shiftId = Long.valueOf(headers.get("shiftId"));
        Shift shiftBefore= shiftService.getShiftBefore(shiftId);

        if(Objects.isNull(shiftBefore)){
            throw new Exception("No data for the request");
        }else {
            reportValue = productReportService.getReportValue(productId, shiftBefore.getShiftId());
        }

        return reportValue;
    }
}
