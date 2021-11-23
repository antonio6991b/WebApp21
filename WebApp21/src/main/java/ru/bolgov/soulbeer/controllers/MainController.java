package ru.bolgov.soulbeer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.bolgov.soulbeer.model.dto.product.ProductDto;
import ru.bolgov.soulbeer.model.dto.productcategory.ProductCategoryDto;
import ru.bolgov.soulbeer.model.dto.seller.SellerDto;
import ru.bolgov.soulbeer.model.dto.shift.ShiftTemplate;
import ru.bolgov.soulbeer.model.dto.shop.ShopDto;
import ru.bolgov.soulbeer.model.entity.*;
import ru.bolgov.soulbeer.repository.MakerDebtRepository;
import ru.bolgov.soulbeer.repository.MakerRepository;
import ru.bolgov.soulbeer.repository.ShopSellersLinkRepository;
import ru.bolgov.soulbeer.service.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Controller
@EnableWebMvc
public class MainController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductReportService productReportService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ShopSellersLinkRepository shopSellersLinkRepository;

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private MakerDebtRepository makerDebtRepository;

    @GetMapping("/main")
    public String main(){
        return "index";
    }


    @GetMapping("fill-products")
    public String fillProduct(Model model){
        return "fill/fillProduct";
    }

    @PostMapping("fill-products")
    public String fill(@RequestParam("file") MultipartFile file){
        System.out.println("call fileservice");
        fileService.fillProducts(file);
        System.out.println("fileservice executed");
        return "redirect:/main";
    }

    @GetMapping("/test")
    public String fillTestData(){
        Random random = new Random();
        List<String> categoryNames = Arrays.asList("Пиво", "Лимонад", "Снеки");
        List<String> beerNames = Arrays.asList("Жигулевское", "Фон Вакано", "Ячменный колос", "Темное", "Светлое", "Полутемное", "Янтарное", "Разливное", "Наливное");
        List<String> limonadNames = Arrays.asList("Буратино", "Кока-кола", "Фанта", "Тархун", "Байкал", "Спрайт", "Жигули");
        List<String> snackNames = Arrays.asList("Сухари", "Фисташки", "Кальмары", "Креветки", "Семечки", "Крабы", "Орешки", "Кириешки");

        List<String> shopNames = Arrays.asList("Управа", "Ставропольская", "Кирова", "Дубовый умет");
        List<String> sellerNames = Arrays.asList("Иван", "Елена", "Света", "Инна", "Евгений", "Руслан", "Константин", "Андрей");

        List<String> makerNames = Arrays.asList("Жигулевский завод", "СамараПив", "Лимонадный");

        for(String makerName:makerNames){
            ProductMaker productMaker = new ProductMaker();
            productMaker.setMakerName(makerName);
            makerRepository.save(productMaker);

            Long makerId = makerRepository.findByName(productMaker.getMakerName()).get(0).getMakerId();
            MakerDebt makerDebt = new MakerDebt();
            makerDebt.setMakerId(makerId);
            makerDebt.setBalance(new BigDecimal(0));
            makerDebt.setTotalSumComing(new BigDecimal(0));
            makerDebt.setTotalSumPay(new BigDecimal(0));
            makerDebtRepository.save(makerDebt);
            System.out.println("save makerDebt, maker_id = " + makerId);
        }

        for(String name: categoryNames){
            ProductCategoryDto productCategory = new ProductCategoryDto();
            productCategory.setCategoryName(name);
            productCategoryService.save(productCategory);
        }

        ProductCategory beers = productCategoryService.findByName(categoryNames.get(0));
        ProductCategory limonads = productCategoryService.findByName(categoryNames.get(1));
        ProductCategory snacks = productCategoryService.findByName(categoryNames.get(2));
        for(String beer:beerNames){
            Long makerId = makerRepository.findByName(makerNames.get(0)).get(0).getMakerId();
            ProductDto product = new ProductDto();
            product.setMakerId(makerId);
            product.setCategoryId(beers.getCategoryId());
            product.setProductName(beer);
            productService.save(product);
        }
        for(String limonad:limonadNames){
            Long makerId = makerRepository.findByName(makerNames.get(1)).get(0).getMakerId();
            ProductDto product = new ProductDto();
            product.setMakerId(makerId);
            product.setCategoryId(limonads.getCategoryId());
            product.setProductName(limonad);
            productService.save(product);
        }
        for(String snack:snackNames){
            Long makerId = makerRepository.findByName(makerNames.get(2)).get(0).getMakerId();
            ProductDto product = new ProductDto();
            product.setMakerId(makerId);
            product.setCategoryId(snacks.getCategoryId());
            product.setProductName(snack);
            productService.save(product);
        }

        for(String shop:shopNames){
            ShopDto newshop = new ShopDto();
            newshop.setShopName(shop);
            shopService.save(newshop);

            for(int i = 0; i < 3; i++){
                long shopId = shopService.findByName(shop).getShopId();
                SellerDto seller = new SellerDto();
                seller.setSellerName(sellerNames.get(random.nextInt(sellerNames.size())));
                sellerService.save(seller);
                int sellersCount = sellerService.findAll().size();
                Long lastSellerId = sellerService.findAll().get(sellersCount - 1).getSellerId();
                ShopSellerLink shopSellerLink = new ShopSellerLink();
                shopSellerLink.setShopId(shopId);
                shopSellerLink.setSellerId(lastSellerId);
                shopSellersLinkRepository.save(shopSellerLink);
            }
        }

        for(String shopForShift:shopNames){
            Shop shop = shopService.findByName(shopForShift);
            Shift shift = new Shift();
            shift.setShopId(shop.getShopId());
            long sellerId = sellerService.findByShopId(shop.getShopId()).get(0).getSellerId();
            shift.setSellerId(sellerId);
            shift.setCashBegin(new BigDecimal(random.nextInt(10000)));
            shift.setCashEnd(shift.getCashBegin().add(new BigDecimal(random.nextInt(1000))));

            ShiftTemplate shiftTemplate = new ShiftTemplate();
            shiftTemplate.setShift(shift);
            shiftService.save(shiftTemplate);
        }

        Long shiftTemplateId = shiftService.findAll().get(0).getShift().getShiftId();
        for (ProductDto productDto : productService.findAll()){
            ProductReport productReport = new ProductReport();
            productReport.setShiftId(shiftTemplateId);
            productReport.setProductId(productDto.getProductId());
            productReport.setPriceBuy(new BigDecimal(random.nextInt(1000)));
            productReport.setPriceSell(productReport.getPriceBuy().add(new BigDecimal(random.nextInt(500))));
            productReport.setRemainsLast(new BigDecimal(100));
            productReport.setComing(new BigDecimal(random.nextInt(50)));
            BigDecimal skladProd = productReport.getRemainsLast().add(productReport.getComing());
            productReport.setRemainsCurrent(skladProd.subtract(new BigDecimal(random.nextInt(skladProd.intValue()))));

            BigDecimal current = productReport.getRemainsLast().add(productReport.getComing()).subtract(productReport.getRemainsCurrent()).multiply(productReport.getPriceSell());

            productReport.setSumCurrent(current);

            BigDecimal profit = productReport.getRemainsLast().add(productReport.getComing()).subtract(productReport.getRemainsCurrent()).multiply(productReport.getPriceSell().subtract(productReport.getPriceBuy()));

            productReport.setGrossProfit(profit);
            int mistake = random.nextInt(100);
            BigDecimal notebookValue = productReport.getSumCurrent();
            if(mistake > 90){
                notebookValue = productReport.getSumCurrent().add(new BigDecimal(100));
            }
            if(mistake<10){
                notebookValue = productReport.getSumCurrent().subtract(new BigDecimal(100));
            }
            if(mistake>95){
                notebookValue = productReport.getSumCurrent().subtract(new BigDecimal(1000));
            }

            productReport.setNotebookValue(notebookValue);

            productReport.setBalance(productReport.getSumCurrent().subtract(notebookValue));

            productReportService.save(productReport);
        }

        return "redirect:/main";
    }

    @GetMapping("/product-list")
    public String getProductList(Model model){
        model.addAttribute("products", fileService.getProductList());

        return "fill/productList";
    }
}
