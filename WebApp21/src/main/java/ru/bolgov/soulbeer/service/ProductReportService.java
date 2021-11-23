package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.report.ProductReportDto;
import ru.bolgov.soulbeer.model.dto.report.WeekReport;
import ru.bolgov.soulbeer.model.dto.report.rest.ReportValue;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.entity.Expense;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductReport;
import ru.bolgov.soulbeer.model.entity.Storage;
import ru.bolgov.soulbeer.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductReportService {

    @Autowired
    private ProductReportRepository productReportRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private StorageRepository storageRepository;


    public void edit(ProductReport productReport, Long id) {
        ProductReport tmp = productReportRepository.findById(id).orElse(null);
        if (Objects.nonNull(tmp)) {
            tmp.setShiftId(productReport.getShiftId());
            tmp.setProductId(productReport.getProductId());
            tmp.setPriceBuy(productReport.getPriceBuy());
            tmp.setPriceSell(productReport.getPriceSell());
            tmp.setRemainsLast(productReport.getRemainsLast());
            tmp.setComing(productReport.getComing());
            tmp.setRemainsCurrent(productReport.getRemainsCurrent());
            tmp.setSumCurrent(productReport.getSumCurrent());
            tmp.setGrossProfit(productReport.getGrossProfit());
            tmp.setNotebookValue(productReport.getNotebookValue());
            tmp.setBalance(productReport.getBalance());
            productReportRepository.save(tmp);
        }
    }

    public WeekReport getWeekReport(Long shiftId){
        WeekReport weekReport = new WeekReport();
        ShiftTemplate shiftTemplate = new ShiftTemplate(shiftRepository.findById(shiftId).get());
        shiftTemplate.setShopName(shopRepository.findById(shiftTemplate.getShift().getShopId()).get().getShopName());
        shiftTemplate.setSellerName(sellerRepository.findById(shiftTemplate.getShift().getSellerId()).get().getSellerName());
        weekReport.setShiftTemplate(shiftTemplate);
        weekReport.setProducts(productReportRepository.findAllByShift(shiftId).stream()
                .map(x -> {
                    ProductReportDto productReportDto = new ProductReportDto();
                    productReportDto.setProductReport(x);
                    productReportDto.setProductName(productRepository.findById(x.getProductId()).get().getProductName());
                    return productReportDto;
                }).collect(Collectors.toList()));
        weekReport.setWriteOffList(expenseRepository.findByType(shiftId, "writeoff"));
        weekReport.setCashDeskList(expenseRepository.findByType(shiftId, "cashdesk"));
        return weekReport;
    }

    public List<Product> getStorageProducts(Long shiftId){
        Long shopId = shiftRepository.findById(shiftId).get().getShopId();
        return storageRepository.findByShopId(shopId).stream()
                .map(x -> productRepository.findById(x.getProductId()).get()).collect(Collectors.toList());
    }

    public void save(ProductReport productReport){
        Long shiftId = productReport.getShiftId();
        Long shopId = shiftRepository.findById(shiftId).get().getShopId();
        List<Storage> storageList = storageRepository.findByShopId(shopId).stream()
                .filter(x -> !x.getProductId().equals(productReport.getProductId()))
                .collect(Collectors.toList());
        BigDecimal storageCount = new BigDecimal(0);
        for (Storage storage : storageList){
            storageCount = storageCount.add(storage.getCount());
        }
        //if(storageCount.compareTo(productReport.))

        productReportRepository.save(productReport);
    }

    public ProductReportDto getProductDto(Long reportId){
        ProductReportDto productReportDto = new ProductReportDto();
        productReportDto.setProductReport(productReportRepository.findById(reportId).get());
        productReportDto.setProductName(productRepository.findById(productReportDto.getProductReport().getProductId()).get().getProductName());
        return productReportDto;
    }

    public ProductReport getProductReport(Long productReportId){
        return productReportRepository.findById(productReportId).get();
    }

    public String getProductName(Long productReportId){
        Long productId = productReportRepository.findById(productReportId).get().getProductId();
        return productRepository.findById(productId).get().getProductName();
    }

    public Long getShiftId(Long productReportId){
        return productReportRepository.findById(productReportId).get().getShiftId();
    }

    public void deleteById(Long productReportId){
        productReportRepository.deleteById(productReportId);
    }

    public ReportValue getReportValue(Long productId, long shiftId){
        ReportValue reportValue = new ReportValue();
        List<ProductReport> report = productReportRepository.findReportValue(productId, shiftId);
        if(report.size()!=0){
            ProductReport tmp = report.get(0);
            reportValue.setRemainsLast(tmp.getRemainsCurrent());
            reportValue.setPriceSell(tmp.getPriceSell());
            reportValue.setPriceBuy(tmp.getPriceBuy());
        }
        return reportValue;
    }

    public void saveExpense(Expense expense, String type){
        expense.setExpenseType(type);
        expenseRepository.save(expense);
    }

    public void editExpense(Expense expense, Long id){
        Expense tmp = expenseRepository.findById(id).orElse(new Expense());
        tmp.setExpenseName(expense.getExpenseName());
        tmp.setExpenseSum(expense.getExpenseSum());
        expenseRepository.save(tmp);
    }
    public void deleteExpense(Long expenseId){
        expenseRepository.deleteById(expenseId);
    }

    public Expense findExpense(Long expenseId){
        return expenseRepository.findById(expenseId).get();
    }
}
