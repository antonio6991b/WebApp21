package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.dao.*;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.Shop;
import ru.bolgov.soulbeer.model.report.ProductReport;
import ru.bolgov.soulbeer.model.shift.Shift;
import ru.bolgov.soulbeer.model.util.ShowDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TestDataService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ProductReportRepository productReportRepository;

    private List<String> names = Arrays.asList("Kirill", "Lena", "Victoria", "Ekaterina", "John", "Evgeniy");
    private List<String> addresses = Arrays.asList(
            "Лазо 145",
            "Ставропольская 21",
            "Ленина 15",
            "Ново-Садовая 50",
            "Советская 2"
    );

    private Random random = new Random();

    public Shop createShop(){
        Shop shop = new Shop();
        shop.setShopAddress(addresses.get(random.nextInt(addresses.size())));
        shop.setShopName(shop.getShopAddress());
        shopRepository.save(shop);
        return shop;
    }

    public Seller createSeller(Shop shop){
        Seller seller = new Seller();
        seller.setSellerName(names.get(random.nextInt(names.size())));
        seller.setShop(shop);
        sellerRepository.save(seller);
        return seller;
    }

    public Shift createShift(Shop shop, LocalDate dateBegin){
        Shift shift = new Shift();
        List<Seller> sellers = sellerRepository.findByShopId(shop.getShopId());
        Seller seller = sellers.get(0);
        shift.setShiftSeller(seller);
        shift.setShiftBegin(Date.valueOf(dateBegin));
        shift.setShiftEnds(Date.valueOf(dateBegin.plusDays(7)));
        shift.setCashBegin(new BigDecimal(random.nextInt(5000)));
        shift.setCashEnd(new BigDecimal(random.nextInt(5000)));
        shiftRepository.save(shift);
        return shift;
    }

    public void createReport(Shift shift){
        List<ProductReport> productReports = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        products.stream()
                .map(x -> {
                    ProductReport productReport = new ProductReport();
                    productReport.setProduct(x);
                    productReport.setPriceSell(new BigDecimal(random.nextInt(1000)));
                    productReport.setPriceBuy(productReport.getPriceSell().add(new BigDecimal(random.nextInt(300))));
                    productReport.setRemainsLast(new BigDecimal(random.nextInt(1000)));
                    productReport.setComing(random.nextInt(1000));
                    productReport.setRemainsCurrent(new BigDecimal(random.nextInt(1000)));
                    productReport.setShift(shift);
                    productReportRepository.save(productReport);
                    return productReport;
                })
                .count();
    }
}
