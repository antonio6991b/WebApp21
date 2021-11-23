package ru.bolgov.soulbeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.soulbeer.model.dto.invoice.InvoiceDto;
import ru.bolgov.soulbeer.model.dto.invoice.InvoiceProductDto;
import ru.bolgov.soulbeer.model.dto.util.ShowDate;
import ru.bolgov.soulbeer.model.entity.Invoice;
import ru.bolgov.soulbeer.model.entity.InvoiceProduct;
import ru.bolgov.soulbeer.model.entity.MakerDebt;
import ru.bolgov.soulbeer.model.entity.Storage;
import ru.bolgov.soulbeer.repository.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private MakerDebtRepository makerDebtRepository;

    public void save(InvoiceDto invoiceDto){
        Invoice invoice = invoiceDto.getInvoice();
        invoice.setDate(invoiceDto.getDate().createDate());
        invoiceRepository.save(invoice);
    }

    public InvoiceDto findById(Long invoiceId){
        InvoiceDto invoiceDto = new InvoiceDto();
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        invoiceDto.setInvoice(invoiceRepository.findById(invoiceId).get());
        invoiceDto.setDate(new ShowDate(invoiceDto.getInvoice().getDate()));
        invoiceDto.setMakerName(makerRepository.findById(invoice.getMakerId()).get().getMakerName());
        invoiceDto.setShopName(shopRepository.findById(invoice.getShopId()).get().getShopName());
        invoiceDto.setProducts(invoiceProductRepository.findByInvoiceId(invoiceId).stream()
                .map(x->{
                    InvoiceProductDto invoiceProductDto = new InvoiceProductDto();
                    invoiceProductDto.setInvoiceProduct(x);
                    invoiceProductDto.setProductName(productRepository.findById(x.getProductId()).get().getProductName());
                    return invoiceProductDto;
                }).collect(Collectors.toList()));
        return invoiceDto;
    }

    public List<InvoiceDto> findAll(){
        return invoiceRepository.findAll().stream()
                .map(x->{
                    InvoiceDto invoiceDto = new InvoiceDto();
                    invoiceDto.setInvoice(x);
                    invoiceDto.setDate(new ShowDate(invoiceDto.getInvoice().getDate()));
                    invoiceDto.setMakerName(makerRepository.findById(x.getMakerId()).get().getMakerName());
                    invoiceDto.setShopName(shopRepository.findById(x.getShopId()).get().getShopName());
                    return invoiceDto;
                }).collect(Collectors.toList());
    }

    public List<InvoiceDto> findByShiftId(Long shiftId){
        return invoiceRepository.findByShiftId(shiftId).stream()
                .map(x->{
                    InvoiceDto invoiceDto = new InvoiceDto();
                    invoiceDto.setInvoice(x);
                    invoiceDto.setDate(new ShowDate(invoiceDto.getInvoice().getDate()));
                    invoiceDto.setMakerName(makerRepository.findById(x.getMakerId()).get().getMakerName());
                    invoiceDto.setShopName(shopRepository.findById(x.getShopId()).get().getShopName());
                    return invoiceDto;
                }).collect(Collectors.toList());
    }

    public InvoiceDto findByShopIdDate(Long shopId, Date date){
        return invoiceRepository.findByShopIdDate(shopId, date).stream()
                .map(x->{
                    InvoiceDto invoiceDto = new InvoiceDto();
                    invoiceDto.setInvoice(x);
                    invoiceDto.setDate(new ShowDate(invoiceDto.getInvoice().getDate()));
                    invoiceDto.setMakerName(makerRepository.findById(x.getMakerId()).get().getMakerName());
                    invoiceDto.setShopName(shopRepository.findById(x.getShopId()).get().getShopName());
                    return invoiceDto;
                }).collect(Collectors.toList()).get(0);
    }

    public void save(InvoiceProduct invoiceProduct){
        Long invoiceId = invoiceProduct.getInvoiceId();
        Long shopId = invoiceRepository.findById(invoiceId).get().getShopId();
        List<Storage> storageList = storageRepository.findByShopId(shopId).stream()
                .filter(x -> !x.getProductId().equals(invoiceProduct.getProductId()))
                .collect(Collectors.toList());
        if(storageList.size()==0){
            Storage storage = new Storage();
            storage.setShopId(shopId);
            storage.setProductId(invoiceProduct.getProductId());
            storage.setCount(invoiceProduct.getCount());
            storage.setPriceBuy(invoiceProduct.getPriceBuy());
            storageRepository.save(storage);
        } else {
            if (storageList.get(0).getPriceBuy().equals(invoiceProduct.getPriceBuy())){
                Storage storageTmp = storageRepository.findById(storageList.get(0).getStorageId()).get();
                storageTmp.setCount(storageTmp.getCount().add(invoiceProduct.getCount()));
                storageRepository.save(storageTmp);
            }
            else {
                Storage storage = new Storage();
                storage.setShopId(shopId);
                storage.setProductId(invoiceProduct.getProductId());
                storage.setCount(invoiceProduct.getCount());
                storage.setPriceBuy(invoiceProduct.getPriceBuy());
                storageRepository.save(storage);
            }
        }

        invoiceProductRepository.save(invoiceProduct);
        MakerDebt makerDebt = makerDebtRepository.findByMakerId(invoiceRepository.findById(invoiceId).get().getMakerId());
        makerDebt.setTotalSumComing(makerDebt.getTotalSumComing().add(invoiceProduct.getPriceBuy().multiply(invoiceProduct.getCount())));
        makerDebt.setBalance(makerDebt.getBalance().subtract(invoiceProduct.getPriceBuy().multiply(invoiceProduct.getCount())));
        makerDebtRepository.save(makerDebt);
    }
}
